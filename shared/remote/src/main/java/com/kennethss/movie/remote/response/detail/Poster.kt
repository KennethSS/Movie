package com.kennethss.movie.remote.response.detail


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Poster(
    @SerialName("aspect_ratio")
    val aspectRatio: Double = 0.0,
    @SerialName("file_path")
    val filePath: String = "",
    @SerialName("height")
    val height: Int = 0,
    @SerialName("iso_639_1")
    val iso6391: String = "",
    @SerialName("vote_average")
    val voteAverage: Double = 0.0,
    @SerialName("vote_count")
    val voteCount: Int = 0,
    @SerialName("width")
    val width: Int = 0
)