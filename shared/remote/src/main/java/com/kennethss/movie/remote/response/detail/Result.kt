package com.kennethss.movie.remote.response.detail


import com.kennethss.movie.data.preview.PreviewData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    @SerialName("id")
    val id: String = "",
    @SerialName("iso_3166_1")
    val iso31661: String = "",
    @SerialName("iso_639_1")
    val iso6391: String = "",
    @SerialName("key")
    val key: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("official")
    val official: Boolean = false,
    @SerialName("published_at")
    val publishedAt: String = "",
    @SerialName("site")
    val site: String = "",
    @SerialName("size")
    val size: Int = 0,
    @SerialName("type")
    val type: String = ""
)

fun Result.toPreviewData() = PreviewData(
    key = key,
    name = name,
    site = site,
    type = type
)