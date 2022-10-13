package com.zalo.movieapp.domain.model

data class MovieDetail(
    val backdropPath: String,
    val homepage: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String
)