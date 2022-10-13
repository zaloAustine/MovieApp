package com.zalo.movieapp.domain.repository


import com.zalo.movieapp.base.Resource
import com.zalo.movieapp.data.local.entities.CastEntity
import com.zalo.movieapp.data.remote.dto.movie.MovieResponse

interface MovieRepository {
    suspend fun getMovies(page:Int):MovieResponse
    suspend fun getMovieCredits(movieId:Int): Resource<List<CastEntity>>
}