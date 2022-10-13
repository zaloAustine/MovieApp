package com.zalo.movieapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.map
import androidx.recyclerview.widget.GridLayoutManager
import com.zalo.movieapp.data.local.entities.toMovie
import com.zalo.movieapp.databinding.FragmentHomeBinding
import com.zalo.movieapp.domain.model.Movie
import com.zalo.movieapp.domain.pagging.MovieAdapter
import com.zalo.movieapp.domain.pagging.MovieLoadStateAdapter
import com.zalo.movieapp.domain.pagging.OnMovieClick
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment : Fragment(), OnMovieClick {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMovieRecyclerView()

        lifecycleScope.launchWhenCreated {
            homeViewModel.state.collect {
                if (it.error.isNotEmpty()) {
                    Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                }
            }
        }

        getMovies()
    }

    private fun getMovies() {
        lifecycleScope.launchWhenCreated {
            homeViewModel.getMovies().collectLatest {
                movieAdapter.submitData(it.map { it.toMovie() })
            }
        }
    }

    private fun initMovieRecyclerView() {
        movieAdapter = MovieAdapter(this)

        binding.moviesRecyclerview.adapter = movieAdapter.withLoadStateHeaderAndFooter(
            header = MovieLoadStateAdapter { movieAdapter.retry() },
            footer = MovieLoadStateAdapter { movieAdapter.retry() }
        )

        movieAdapter.addLoadStateListener { loadState ->
            loadState.mediator?.refresh
            with(binding) {
                swipeRefreshLayout.isRefreshing = loadState.refresh is LoadState.Loading
                swipeRefreshLayout.isRefreshing = loadState.refresh is LoadState.Error
                handleError(loadState)
            }
        }

        binding.moviesRecyclerview.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = movieAdapter
        }
    }

    private fun handleError(loadState: CombinedLoadStates) {
        val errorState = loadState.source.append as? LoadState.Error
            ?: loadState.source.prepend as? LoadState.Error
        errorState?.let {
            Toast.makeText(context, "${it.error}", Toast.LENGTH_LONG).show()
        }

        binding.retryButton.setOnClickListener {
            getMovies()
        }
    }

    override fun click(movie: Movie) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(movie)
        findNavController().navigate(action)
    }
}