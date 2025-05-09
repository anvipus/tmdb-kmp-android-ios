package com.anvipus.explore.kmp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PopularListData(
    @SerialName("page")
    val page: Int? = null,
    @SerialName("results")
    val results: List<Movie>? = null,
    @SerialName("total_pages")
    val totalPages: Int? = null,
    @SerialName("total_results")
    val totalResults: Int? = null
)