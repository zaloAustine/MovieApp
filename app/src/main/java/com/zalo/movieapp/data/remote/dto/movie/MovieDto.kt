package com.zalo.movieapp.data.remote.dto.movie

import NullToEmptyString
import com.squareup.moshi.Json
import com.zalo.movieapp.data.local.entities.MoviesEntity
import com.zalo.movieapp.domain.model.Movie

data class MovieDto(
    val adult: Boolean,
    @Json(name = "backdrop_path")
    @NullToEmptyString
    val backdropPath: String,
    val id: Int,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "release_date")
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "vote_count")
    val voteCount: Int
)

fun MovieDto.toMovie(): Movie {
    return Movie(
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        voteCount = voteCount,
        id = id.toLong(),
        voteAverage = voteAverage
    )
}

fun MovieDto.toMovieEntity(): MoviesEntity {
    return MoviesEntity(
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        voteCount = voteCount,
        voteAverage = voteAverage,
        remoteId = id
    )
}