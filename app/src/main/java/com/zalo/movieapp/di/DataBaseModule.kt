package com.zalo.movieapp.di

import android.content.Context
import androidx.room.Room
import com.zalo.movieapp.data.local.database.MovieDao
import com.zalo.movieapp.data.local.database.MovieDatabase
import com.zalo.movieapp.data.local.database.RemoteKeyDao
import com.zalo.movieapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): MovieDatabase {
        return Room.databaseBuilder(
            appContext,
            MovieDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao {
        return movieDatabase.movieDao()
    }

    @Provides
    fun provideKeysDao(movieDatabase: MovieDatabase): RemoteKeyDao {
        return movieDatabase.remoteKeyDao()
    }
}