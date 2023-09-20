package com.zahran.movieapp.domain.usecase

import com.zahran.movieapp.data.source.remote.Resource
import com.zahran.movieapp.domain.model.Movie
import com.zahran.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetPopularMovies (
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Flow<Resource<List<Movie>>> {
        return movieRepository.getPopularMovies()
    }
}