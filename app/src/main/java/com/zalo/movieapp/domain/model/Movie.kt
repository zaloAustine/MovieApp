package com.zalo.movieapp.domain.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val backdropPath: String?,
    val id: Long,
    val originalLanguage: String,
    var originalTitle: String,
    val overview: String,
    val posterPath: String?,
    val releaseDate: String,
    val title: String,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "vote_count")
    val voteCount: Int,
    ): Parcelable

