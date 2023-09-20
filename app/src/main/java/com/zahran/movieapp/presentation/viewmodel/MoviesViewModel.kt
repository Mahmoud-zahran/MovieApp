package com.zahran.movieapp.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zahran.movieapp.data.source.remote.Resource
import com.zahran.movieapp.domain.usecase.GetPopularMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getPopularMovies: GetPopularMovies
) : ViewModel() {

    private val _state = mutableStateOf(MovieUiState())
    val state: State<MovieUiState> = _state

    init {
        getMovies()
    }

    fun getMovies() {
        viewModelScope.launch {

            getPopularMovies().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            moviesList = result.data ?: emptyList(),
                            isLoading = true
                        )
                    }
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            moviesList = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            moviesList = result.data ?: emptyList(),
                            isLoading = false
                        )

                    }
                }
            }.launchIn(this)
        }
    }
}