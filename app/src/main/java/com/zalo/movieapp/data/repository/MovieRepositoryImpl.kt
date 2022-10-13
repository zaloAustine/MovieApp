package com.zalo.movieapp.data.repository

import com.zalo.movieapp.base.Resource
import com.zalo.movieapp.data.local.database.MovieDao
import com.zalo.movieapp.data.local.entities.CastEntity
import com.zalo.movieapp.data.remote.api.MovieApi
import com.zalo.movieapp.data.remote.dto.movie.MovieResponse
import com.zalo.movieapp.data.remote.dto.movie.toCastEntity
import com.zalo.movieapp.domain.repository.MovieRepository
import com.zalo.movieapp.util.ErrorHandler
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val dao: MovieDao
) : MovieRepository {

    override suspend fun getMovies(page: Int): MovieResponse {
        return movieApi.getMovies(page = page)
    }

    override suspend fun getMovieCredits(movieId: Int): Resource<List<CastEntity>> {
        return try {
            val cast = movieApi.getMovieCredits(movieId).cast
            dao.insertAllCasts(cast.map { it.toCastEntity() })
            Resource.Success(dao.getLocalCast())
        } catch (e: Exception) {
            Resource.Error(ErrorHandler.handleErrors(e), dao.getLocalCast())
        }
    }
}