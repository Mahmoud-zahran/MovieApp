package com.zahran.movieapp.di.moviesmodule

import com.zahran.movieapp.data.repository.MovieRepositoryImpl
import com.zahran.movieapp.data.source.local.roomdb.dao.MovieDao
import com.zahran.movieapp.data.source.local.roomdb.dao.PopularMoviesDao
import com.zahran.movieapp.data.source.remote.MovieApiService
import com.zahran.movieapp.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MoviesRepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepositoryImpl(
        movieApiService: MovieApiService,
        popularMoviesDao: PopularMoviesDao,
        movieDao: MovieDao
    ): MovieRepository = MovieRepositoryImpl(movieApiService, popularMoviesDao, movieDao)

}