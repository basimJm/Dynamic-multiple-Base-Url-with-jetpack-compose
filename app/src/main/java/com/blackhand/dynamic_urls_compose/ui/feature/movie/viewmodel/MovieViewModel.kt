package com.blackhand.dynamic_urls_compose.ui.feature.movie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blackhand.dynamic_urls_compose.core.common.ResponseState
import com.blackhand.dynamic_urls_compose.domain.usecase.MovieUseCase
import com.blackhand.dynamic_urls_compose.ui.feature.movie.state.MovieState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {

    private var _movies = MutableStateFlow(MovieState())
    val movies: StateFlow<MovieState>
        get() = _movies

    init {
        getMoviesList()
    }

    fun getMoviesList() {
        movieUseCase.invoke().onEach { response ->
            when (response) {
                is ResponseState.Loading -> {
                    _movies.value = MovieState().copy(isLoading = true)
                }

                is ResponseState.Error -> {
                    _movies.value = MovieState().copy(errorMessage = response.message)
                }

                is ResponseState.Success -> {
                    _movies.value = MovieState().copy(moviesList = response.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}