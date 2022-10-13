package com.zalo.movieapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.zalo.movieapp.data.local.database.MovieDao
import com.zalo.movieapp.data.local.database.RemoteKeyDao
import com.zalo.movieapp.data.local.entities.MoviesEntity
import com.zalo.movieapp.domain.pagging.MovieRemoteMediator
import com.zalo.movieapp.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@OptIn(ExperimentalPagingApi::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieDao: MovieDao,
    private val remoteKeyDao: RemoteKeyDao
) : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    val state: StateFlow<HomeScreenState> = _state.asStateFlow()

    fun getMovies(): Flow<PagingData<MoviesEntity>> {
        return Pager(
            config = PagingConfig(pageSize = 30),
            remoteMediator = MovieRemoteMediator(
                movieRepository = movieRepository,
                remoteKeyDao = remoteKeyDao,
                movieDao = movieDao
            ),
            pagingSourceFactory = { movieDao.getPagedLocalMovies() }
        ).flow.cachedIn(viewModelScope)
    }
}

