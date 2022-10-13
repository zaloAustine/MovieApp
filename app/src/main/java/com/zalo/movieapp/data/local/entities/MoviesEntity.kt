package com.zalo.movieapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.zalo.movieapp.domain.model.Movie
import com.zalo.movieapp.util.Constants

@Entity(tableName = Constants.MOVIES_TABLE)
data class MoviesEntity(
    @Json(name = "id")
    val remoteId: Int,
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val backdropPath: String?,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String?,
    val releaseDate: String,
    val title: String,
    val voteCount: Int,
    val voteAverage: Double,
    )

fun MoviesEntity.toMovie():Movie{
    return Movie(
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        voteCount = voteCount,
        id = id,
        voteAverage = voteAverage
    )
}