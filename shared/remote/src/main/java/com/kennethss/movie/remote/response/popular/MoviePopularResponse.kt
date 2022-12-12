package com.kennethss.movie.remote.response.popular

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviePopularResponse(
    @SerialName("page")
    val page: Int = 0,
    @SerialName("results")
    val results: List<MoviePopularItemResponse> = listOf(),
    @SerialName("total_pages")
    val totalPages: Int = 0,
    @SerialName("total_results")
    val totalResults: Int = 0
)


