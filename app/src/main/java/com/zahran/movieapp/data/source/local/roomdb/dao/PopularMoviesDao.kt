package com.zahran.movieapp.data.source.local.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zahran.movieapp.data.source.local.roomdb.entity.PopularMoviesEntity

@Dao
interface PopularMoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMovies(movies: PopularMoviesEntity)

//    @Query("INSERT ${movie} FROM ${MovieEntity.TABLE_NAME}")
//    suspend fun insertMovie(movie: MovieEntity)

    @Query("SELECT * FROM ${PopularMoviesEntity.TABLE_NAME}")
    suspend fun getPopularMovieList(): List<PopularMoviesEntity>

    @Query("DELETE FROM ${PopularMoviesEntity.TABLE_NAME}")
    suspend fun deleteAll()
}
