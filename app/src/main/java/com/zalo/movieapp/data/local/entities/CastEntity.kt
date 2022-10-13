package com.zalo.movieapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zalo.movieapp.domain.model.Cast
import com.zalo.movieapp.util.Constants

@Entity(tableName = Constants.CAST_TABLE)
data class CastEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val adult: Boolean,
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val gender: Int,
    val known_for_department: String,
    val name: String,
    val order: Int,
    val original_name: String,
    val popularity: Double,
    val profile_path: String?
)

fun CastEntity.toCast(): Cast {
    return Cast(
        adult,
        cast_id,
        character,
        credit_id,
        gender,
        id,
        known_for_department,
        name,
        order,
        original_name,
        popularity,
        profile_path
    )
}