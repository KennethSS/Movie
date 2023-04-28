package com.kennethss.movie.data.movie

import com.kennethss.movie.domain.movie.movie.MovieThumbnail

data class MovieThumbnailData(
    val id: Int,
    val name: String,
    val posterUrl: String
)

fun MovieThumbnailData.toDomain() = MovieThumbnail(
    id = id,
    name = name,
    posterUrl = posterUrl
)