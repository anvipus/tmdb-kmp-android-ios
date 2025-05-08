@file:Suppress("unused")

package com.anvipus.explore.kmp.repository

import com.anvipus.explore.kmp.db.getDatabase
import com.anvipus.explore.kmp.db.movieDao
import com.anvipus.explore.kmp.network.GeneralApi
import com.anvipus.explore.kmp.network.provideHttpClient
import com.anvipus.explore.kmp.util.Constants

/**
 * Factory function untuk membuat instance MovieRepositoryBridge,
 * yang bisa dipanggil dari Swift menggunakan interop.
 */
fun createMovieRepository(): MovieRepositoryBridge {
    // Inisialisasi HttpClient
    val client = provideHttpClient()

    // Inisialisasi API service
    val apiService = GeneralApi(client)

    // Inisialisasi DAO
    val movieDao = getDatabase().movieDao

    // API key dari konstanta
    val apiKey = Constants.API_KEY

    // Kembalikan instance repository yang siap digunakan
    return MovieRepositoryBridge(
        apiService = apiService,
        movieDao = movieDao,
        apiKey = apiKey
    )
}
