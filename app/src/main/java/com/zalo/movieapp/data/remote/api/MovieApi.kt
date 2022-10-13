package com.zalo.movieapp.data.remote.api

import com.zalo.movieapp.data.remote.dto.movie.CreditResponse
import com.zalo.movieapp.data.remote.dto.movie.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("discover/movie")
    suspend fun getMovies(
        @Query("page") page: Int = 1
    ): MovieResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movie_id: Int
    ): CreditResponse

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movie_id: Int,
        @Query ("api_key") apiKey : String
    ): Response<MovieResponse>

}