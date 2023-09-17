package com.zahran.movieapp.data.source.local.roomdb.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zahran.movieapp.domain.model.PopularMovies

@Entity(tableName = PopularMoviesEntity.TABLE_NAME)
data class PopularMoviesEntity(@PrimaryKey(autoGenerate = true)
                               val primaryKeyId: Int? = null,
                               val page: Int,
                               val results: List<MovieEntity>) {
    fun toPopularMovies(): PopularMovies {
        return PopularMovies(
            page = page,
            results = results.map { it.toMovie() }
        )
    }

    companion object {
        const val TABLE_NAME = "popular_movies"
    }
}
