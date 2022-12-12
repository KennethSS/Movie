package com.kennethss.movie.remote.response.detail


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Images(
    @SerialName("posters")
    val posters: List<Poster> = listOf()
)