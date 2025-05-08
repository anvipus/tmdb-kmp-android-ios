package com.anvipus.explore.kmp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class YoutubeTrailerListData(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("results")
    val results: List<YoutubeTrailer>? = null
)