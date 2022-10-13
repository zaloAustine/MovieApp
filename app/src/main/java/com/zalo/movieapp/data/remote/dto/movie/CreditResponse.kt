package com.zalo.movieapp.data.remote.dto.movie

data class CreditResponse(
    val cast: List<CastDto>,
    val id: Int
)