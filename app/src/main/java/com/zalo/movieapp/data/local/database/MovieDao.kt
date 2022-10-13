package com.zalo.movieapp.data.local.database

import androidx.paging.PagingSource
import androidx.room.*
import com.zalo.movieapp.data.local.entities.CastEntity
import com.zalo.movieapp.data.local.entities.MoviesEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    suspend fun getLocalMovies(): List<MoviesEntity>

    @Query("SELECT * FROM movies")
    fun getPagedLocalMovies(): PagingSource<Int,MoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(movieLocal: List<MoviesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCasts(cast: List<CastEntity>)

    @Query("SELECT * FROM casts")
    suspend fun getLocalCast(): List<CastEntity>

    @Query("DELETE FROM movies")
    suspend fun clearMovies()
}