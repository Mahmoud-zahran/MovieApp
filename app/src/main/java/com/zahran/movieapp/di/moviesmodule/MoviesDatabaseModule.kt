package com.zahran.movieapp.di.moviesmodule

import android.app.Application
import com.zahran.movieapp.data.source.local.roomdb.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 *
 * Here we have defined a Hilt module called MoviesDatabaseModule
 * which is annotated with @Module and @InstallIn(SingletonComponent::class).
 * This means that this module will be installed in the SingletonComponent
 * which has the scope of the entire application.
 * By using these @Provides methods,
 * we can provide these dependencies to any component in our app
 * by simply annotating the constructor of that component with @Inject.

For example,
 * if we want to use PopularMoviesDao in our MovieRepository,
 * we can simply annotate the constructor of MovieRepository
 * with @Inject and pass PopularMoviesDao as a parameter:

 */
@Module
@InstallIn(SingletonComponent::class)
class MoviesDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = MovieDatabase.getDatabase(application)

    @Singleton
    @Provides
    fun providePopularMoviesDao(database: MovieDatabase) =
        database.getPopularMoviesDao()

    @Singleton
    @Provides
    fun provideMovieDao(database: MovieDatabase) =
        database.getMovieDao()
}