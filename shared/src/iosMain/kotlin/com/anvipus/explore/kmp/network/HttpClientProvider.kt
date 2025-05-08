package com.anvipus.explore.kmp.network

import com.anvipus.explore.kmp.util.Constants
import io.ktor.client.*
import io.ktor.client.engine.darwin.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

actual fun provideHttpClient(): HttpClient {
    return HttpClient(Darwin) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }

        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = Constants.BASE_URL
            }
            headers.append(HttpHeaders.ContentType, ContentType.Application.Json)
        }

        // Tambahan opsional untuk konfigurasi iOS
        engine {
            configureRequest {
                setAllowsCellularAccess(true)
            }
        }
    }
}
