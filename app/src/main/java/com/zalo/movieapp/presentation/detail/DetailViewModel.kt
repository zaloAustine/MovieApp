package com.zalo.movieapp.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.zalo.movieapp.base.Resource
import com.zalo.movieapp.data.local.entities.toCast
import com.zalo.movieapp.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@OptIn(ExperimentalPagingApi::class)
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DetailScreenState())
    val state: StateFlow<DetailScreenState> = _state.asStateFlow()


    fun getCast(movieId: Int) {
        viewModelScope.launch {
            when (val response = movieRepository.getMovieCredits(movieId)) {
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        cast = response.data!!.map { it.toCast() }
                    )
                }
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        cast = response.data!!.map { it.toCast() },
                        error = response.message ?: "An Error Occurred"
                    )
                }
            }
        }
    }
}

