package com.zalo.movieapp.data.remote.dto.movie

import com.squareup.moshi.Json

data class MovieResponse(
    val page: Int,
    val results: List<MovieDto>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)