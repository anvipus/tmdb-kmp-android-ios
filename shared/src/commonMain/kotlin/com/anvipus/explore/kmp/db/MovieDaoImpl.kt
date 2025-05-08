package com.anvipus.explore.kmp.db

import com.anvipus.explore.kmp.data.model.Movie
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import app.cash.sqldelight.coroutines.asFlow
import com.anvipus.explore.kmp.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.anvipus.explore.kmp.mapper.toMovie

class MovieDaoImpl(private val queries: MoviesQueries) : MovieDao {
    override fun getAllMovies(): Flow<List<Movies>> =
        queries.selectAll()
            .asFlow()
            .map { query -> query.executeAsList() }


    override suspend fun insertMovie(movie: Movie) {
        queries.insertMovie(
            id = movie.toEntity().id ?: 0L,
            adult = movie.toEntity().adult ,
            backdrop_path = movie.toEntity().backdrop_path ?: "",
            original_language = movie.toEntity().original_language ?: "",
            original_title = movie.toEntity().original_title ?: "",
            overview = movie.toEntity().overview ?: "",
            popularity = movie.toEntity().popularity ?: 0.0,
            poster_path = movie.toEntity().poster_path ?: "",
            release_date = movie.toEntity().release_date ?: "",
            title = movie.toEntity().title ?: "",
            video = movie.toEntity().video,
            vote_average = movie.toEntity().vote_average ?: 0.0,
            vote_count = movie.toEntity().vote_count ?: 0.0
        )
    }


    override suspend fun deleteAll() {
        queries.deleteAll()
    }
}
