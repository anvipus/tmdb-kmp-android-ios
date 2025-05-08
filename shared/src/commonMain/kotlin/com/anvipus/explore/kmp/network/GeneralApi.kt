package com.anvipus.explore.kmp.network

import com.anvipus.explore.kmp.data.model.PopularListData
import com.anvipus.explore.kmp.data.model.YoutubeTrailerListData
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class GeneralApi(private val client: HttpClient) {

    suspend fun getPopularMovies(auth: String, language: String = "en-US", page: String = "1"): PopularListData {
        return client.get("3/movie/popular") {
            headers { append(HttpHeaders.Authorization, auth) }
            parameter("language", language)
            parameter("page", page)
        }.body()
    }

    suspend fun getNowPlayingMovies(auth: String, language: String = "en-US", page: String = "1"): PopularListData {
        return client.get("3/movie/now_playing") {
            headers { append(HttpHeaders.Authorization, auth) }
            parameter("language", language)
            parameter("page", page)
        }.body()
    }

    suspend fun getSearchMovies(auth: String, params: Map<String, String>): PopularListData {
        return client.get("3/search/movie") {
            headers { append(HttpHeaders.Authorization, auth) }
            params.forEach { (key, value) -> parameter(key, value) }
        }.body()
    }

    suspend fun getSearchMovies2(auth: String, params: Map<String, String>): PopularListData {
        return getSearchMovies(auth, params) // alias ke fungsi yg sama
    }

    suspend fun getVideoTrailer(accessToken: String, movieId: String, params: Map<String, String>): YoutubeTrailerListData {
        return client.get("3/movie/$movieId/videos") {
            headers { append(HttpHeaders.Authorization, accessToken) }
            params.forEach { (key, value) -> parameter(key, value) }
        }.body()
    }
}
