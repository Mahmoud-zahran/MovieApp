package com.zahran.movieapp.data.source.remote.dto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.zahran.movieapp.data.source.local.roomdb.entity.PopularMoviesEntity

@JsonClass(generateAdapter = true)
data class PopularMoviesDto(@Json(name = "page")
                            val page: Int,
                            @Json(name = "results")
                            val results: List<MovieDto>,
                            @Json(name = "total_pages")
                            val totalPages: Int,
                            @Json(name = "total_results")
                            val totalResults: Int){
    fun toPopularMoviesEntity(): PopularMoviesEntity {
        return PopularMoviesEntity(
            page = page,
            //getting results from remote as a list of movieDto and
            // add it to popularMovieEntity as a list of MovieEntity
            results = results.map { it.toMovieEntity() }
        )
    }
}
