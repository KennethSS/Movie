package com.kennethss.movie.data.actor

import com.kennethss.movie.domain.movie.actor.Actor

data class ActorData(
    val id: Int,
    val name: String,
    val profileUrl: String,
    val character: String
)

fun ActorData.toDomain() = Actor(
    id = id,
    name = name,
    profileUrl = profileUrl,
    character = character
)