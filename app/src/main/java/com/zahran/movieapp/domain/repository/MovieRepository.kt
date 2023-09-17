package com.zahran.movieapp.domain.repository

import com.zahran.movieapp.data.source.remote.Resource
import com.zahran.movieapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(): Flow<Resource<List<Movie>>>

}