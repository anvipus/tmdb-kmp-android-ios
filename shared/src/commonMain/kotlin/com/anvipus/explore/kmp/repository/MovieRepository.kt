@file:Suppress("unused")

package com.anvipus.explore.kmp.repository

import com.anvipus.explore.kmp.data.model.Movie
import com.anvipus.explore.kmp.data.model.PopularListData
import com.anvipus.explore.kmp.data.model.Resource
import com.anvipus.explore.kmp.data.model.YoutubeTrailerListData
import com.anvipus.explore.kmp.db.MovieDao
import com.anvipus.explore.kmp.db.Movies
import com.anvipus.explore.kmp.network.GeneralApi
import com.anvipus.explore.kmp.util.Constants
import com.anvipus.explore.kmp.util.ApiHelper
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

/**
 * Bridge class untuk Swift
 */
class MovieRepositoryBridge(
    private val apiService: GeneralApi,
    private val movieDao: MovieDao,
    private val apiKey: String
) {

    fun getPopularMovies(): List<Movie> = runBlocking {
        apiService.getPopularMovies("${Constants.BEARER}$apiKey").results ?: emptyList()
    }

    fun getNowPlayingMovies(): List<Movie> = runBlocking {
        apiService.getNowPlayingMovies("${Constants.BEARER}$apiKey").results ?: emptyList()
    }

    fun searchMovies(query: String): List<Movie> = runBlocking {
        val params = mapOf(
            "language" to "en-US",
            "page" to "1",
            "query" to query
        )
        apiService.getSearchMovies("${Constants.BEARER}$apiKey", params).results ?: emptyList()
    }

    fun videoTrailer(videoId: String): Resource<YoutubeTrailerListData> = runBlocking {
        val params = mapOf("language" to "en-US")
        ApiHelper.call<YoutubeTrailerListData, YoutubeTrailerListData> {
            apiService.getVideoTrailer(
                accessToken = "${Constants.BEARER}$apiKey",
                params = params,
                movieId = videoId
            )
        }.first()
    }

    fun getAllMovies(): List<Movies> = runBlocking {
        movieDao.getAllMovies().first() // Ambil nilai pertama dari Flow
    }

    fun insertMovie(movie: Movie) = runBlocking {
        movieDao.insertMovie(movie)
    }

    fun deleteAllMovies() = runBlocking {
        movieDao.deleteAll()
    }

    fun searchMovies2(query: String): Resource<PopularListData> = runBlocking {
        val params = mapOf(
            "language" to "en-US",
            "page" to "1",
            "query" to query
        )
        ApiHelper.call<PopularListData, PopularListData> {
            apiService.getSearchMovies2("${Constants.BEARER}$apiKey", params)
        }.first()
    }

}
