package com.kennethss.movie.data.movie.preview

import com.kennethss.movie.domain.movie.preview.Preview

data class PreviewData(
    val key: String,
    val name: String,
    val site: String,
    val type: String
)

fun PreviewData.toDomain() = Preview(
    key = key,
    name = name,
    site = when (site) {
        "YouTube" -> Preview.Site.YOUTUBE
        else -> Preview.Site.YOUTUBE
    },
    type = when (type) {
        "Trailer" -> Preview.Type.TRAILER
        "Teaser" -> Preview.Type.TEASER
        else -> Preview.Type.FEATURETTE
    }
)