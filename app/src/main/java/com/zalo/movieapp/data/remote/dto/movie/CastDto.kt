package com.zalo.movieapp.data.remote.dto.movie

import com.zalo.movieapp.data.local.entities.CastEntity

data class CastDto(
    val adult: Boolean,
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val order: Int,
    val original_name: String,
    val popularity: Double,
    val profile_path: String?
)

fun CastDto.toCastEntity(): CastEntity {
    return CastEntity(
        id,
        adult,
        cast_id,
        character,
        credit_id,
        gender,
        known_for_department,
        name,
        order,
        original_name,
        popularity,
        profile_path
    )
}