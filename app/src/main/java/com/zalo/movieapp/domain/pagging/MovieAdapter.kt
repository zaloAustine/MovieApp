package com.zalo.movieapp.domain.pagging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.zalo.movieapp.databinding.MovieItemBinding
import com.zalo.movieapp.domain.model.Movie
import com.zalo.movieapp.util.Constants.Companion.IMAGE_URL
import java.lang.Exception

class MovieAdapter( val listener: OnMovieClick):PagingDataAdapter<Movie,MovieAdapter.MovieViewHolder>(DiffUtilCallBack()) {
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let {movie ->
            holder.bind(movie)
            val itemHolder = holder as MovieViewHolder
            itemHolder.itemView.setOnClickListener {
                listener.click(movie = movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder{
        val view = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    class MovieViewHolder(private val binding: MovieItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(movie:Movie){

            var url  = IMAGE_URL
            url += if (!movie.posterPath.isNullOrEmpty()) {
                movie.posterPath
            } else movie.backdropPath

            Picasso.get().load(url)
                .into(binding.movieBackDropImageView,object :Callback{
                    override fun onSuccess() {
                        binding.progressImageView.visibility  = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        binding.progressImageView.visibility  = View.GONE
                    }
                })

            with(binding){
                movieTitleTextView.text = movie.title
                voteCountTextView.text = movie.voteAverage.toString()
            }
        }
    }

    class DiffUtilCallBack:DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.originalTitle == newItem.originalTitle
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.originalTitle == newItem.originalTitle && oldItem.id == newItem.id
        }
    }
}

interface OnMovieClick{
    fun click(movie: Movie)
}