package com.kennethss.movie.data.director

import com.kennethss.movie.domain.movie.director.Director

data class DirectorData(
    val id: Int,
    val name: String,
    val profileUrl: String
)

fun DirectorData.toDomain() = Director(
    id = id,
    name = name,
    profileUrl = profileUrl
)