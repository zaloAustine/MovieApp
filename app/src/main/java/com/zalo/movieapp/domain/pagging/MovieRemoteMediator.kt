package com.zalo.movieapp.domain.pagging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.zalo.movieapp.data.local.database.MovieDao
import com.zalo.movieapp.data.local.database.RemoteKeyDao
import com.zalo.movieapp.data.local.entities.MoviesEntity
import com.zalo.movieapp.data.local.entities.RemoteKey
import com.zalo.movieapp.data.remote.dto.movie.toMovieEntity
import com.zalo.movieapp.domain.repository.MovieRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator @Inject constructor(
    private val movieDao: MovieDao,
    private val remoteKeyDao: RemoteKeyDao,
    private val movieRepository: MovieRepository
) : RemoteMediator<Int, MoviesEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MoviesEntity>
    ): MediatorResult {
        val pageKeyData = getKeyPageData(loadType, state)
        val page = when (pageKeyData) {
            is MediatorResult.Success -> {
                return pageKeyData
            }
            else -> {
                pageKeyData as Int
            }
        }
        return try {
            val response = movieRepository.getMovies(page)
            val isEndOfList = response.results.isEmpty()

            if (loadType == LoadType.REFRESH) {
                movieDao.clearMovies()
                remoteKeyDao.deleteByQuery()
            }
            val prevKey = if (page == 1) null else page - 1
            val nextKey = if (isEndOfList) null else page + 1


            movieDao.insertAllMovies(response.results.map { it.toMovieEntity() })

            val keys = mutableListOf<RemoteKey>()
            movieDao.getLocalMovies().map {
                keys.add(RemoteKey(it.id.toString(), prevKey = prevKey, nextKey = nextKey))
            }

            remoteKeyDao.insertOrReplace(keys)

            return MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getKeyPageData(loadType: LoadType, state: PagingState<Int, MoviesEntity>): Any {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                val nextKey = remoteKeys?.nextKey
                return nextKey ?: MediatorResult.Success(endOfPaginationReached = false)
            }
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)
                val prevKey = remoteKeys?.prevKey ?: return MediatorResult.Success(
                    endOfPaginationReached = false
                )
                prevKey
            }
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, MoviesEntity>): RemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id->
                remoteKeyDao.remoteKeyByQuery(id.toString())
            }
        }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, MoviesEntity>): RemoteKey? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { movie -> remoteKeyDao.remoteKeyByQuery(movie.id.toString()) }
    }

    private suspend fun getFirstRemoteKey(state: PagingState<Int, MoviesEntity>): RemoteKey? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { movie -> remoteKeyDao.remoteKeyByQuery(movie.id.toString()) }
    }
}