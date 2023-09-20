package com.zahran.movieapp.presentation.viewmodel

import com.zahran.movieapp.domain.model.Movie

data class MovieUiState(
    val moviesList: List<Movie> = emptyList(),
    val isLoading: Boolean = false
)
