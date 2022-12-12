package com.kennethss.movie.domain.movie.preview

data class Preview(
    val key: String,
    val name: String,
    val site: Site,
    val type: Type
) {
    enum class Type {
        TRAILER,
        TEASER,
        FEATURETTE
    }

    enum class Site {
        YOUTUBE
    }
}