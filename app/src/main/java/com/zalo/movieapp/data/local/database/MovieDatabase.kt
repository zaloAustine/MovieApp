package com.zalo.movieapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zalo.movieapp.data.local.entities.CastEntity
import com.zalo.movieapp.data.local.entities.MoviesEntity
import com.zalo.movieapp.data.local.entities.RemoteKey

@Database(entities = [MoviesEntity::class,RemoteKey::class,CastEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun remoteKeyDao():RemoteKeyDao
}