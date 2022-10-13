package com.zalo.movieapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_key")
data class RemoteKey(@PrimaryKey val id:String, val prevKey:Int?, val nextKey:Int?)