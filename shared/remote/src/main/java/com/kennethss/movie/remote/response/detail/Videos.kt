package com.kennethss.movie.remote.response.detail


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Videos(
    @SerialName("results")
    val results: List<Result> = listOf()
)