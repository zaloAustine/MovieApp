package com.zalo.movieapp.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.zalo.movieapp.databinding.FragmentDetailBinding
import com.zalo.movieapp.domain.model.Cast
import com.zalo.movieapp.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()

    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel.getCast(args.movie.id.toInt())
        setupDetails()

        lifecycleScope.launchWhenCreated {
            detailViewModel.state.collect {
                binding.progressBar.isVisible = it.isLoading
                if (it.error.isNotEmpty()) {
                    Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                }
                setupCast(it.cast)
            }
        }
    }

    private fun setupCast(cast: List<Cast>) {
        val castAdapter = CastAdapter()
        binding.castRecyclerview.apply {
            layoutManager = GridLayoutManager(context,1,LinearLayoutManager.HORIZONTAL,false)
            adapter = castAdapter
        }
        castAdapter.submitList(cast)
    }

    private fun setupDetails() {
        args.movie.let {
            var url = Constants.IMAGE_URL
            url += if (!it.posterPath.isNullOrEmpty()) {
                it.posterPath
            } else it.backdropPath
            Picasso.get().load(url).into(binding.backdropImageView)
            Picasso.get().load(Constants.IMAGE_URL + it.posterPath).into(binding.posterImageView)
            binding.voteCountTextView.text = it.voteCount.toString()
            binding.movieTitleTextView.text = it.title
            binding.movieDescriptionTextView.text = it.overview
        }
    }
}