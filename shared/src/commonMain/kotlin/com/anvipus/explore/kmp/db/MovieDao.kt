package com.anvipus.explore.kmp.db

import com.anvipus.explore.kmp.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieDao {
    fun getAllMovies(): Flow<List<Movies>>
    suspend fun insertMovie(movie: Movie)
    suspend fun deleteAll()
}
