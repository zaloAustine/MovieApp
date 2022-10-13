package com.zalo.movieapp.presentation.home

import com.zalo.movieapp.domain.model.Genre
import com.zalo.movieapp.domain.model.Movie

data class HomeScreenState(
    val isLoading:Boolean = true,
    val movies:List<Movie> = emptyList(),
    val nowPlayingMovie:List<Movie>? = null,
    val error:String = ""
    )