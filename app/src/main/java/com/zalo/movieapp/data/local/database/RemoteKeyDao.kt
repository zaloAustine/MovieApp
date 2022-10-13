package com.zalo.movieapp.data.local.database

import androidx.room.*
import com.zalo.movieapp.data.local.entities.RemoteKey

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: List<RemoteKey>)

    @Query("SELECT * FROM remote_key WHERE id = :id")
    suspend fun remoteKeyByQuery(id: String): RemoteKey

    @Query("DELETE FROM remote_key")
    suspend fun deleteByQuery()
}