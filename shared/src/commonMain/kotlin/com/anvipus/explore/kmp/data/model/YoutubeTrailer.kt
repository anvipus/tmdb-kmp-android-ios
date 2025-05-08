package com.anvipus.explore.kmp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class YoutubeTrailer(
    @SerialName("name")
    val name: String? = null,
    @SerialName("key")
    val key: String? = null,
    @SerialName("site")
    val site: String? = null,
    @SerialName("id")
    val id: String? = null
)