package com.kennethss.movie.data.movie

import com.kennethss.movie.data.movie.preview.PreviewData
import com.kennethss.movie.data.movie.preview.toDomain
import com.kennethss.movie.domain.actor.Actor
import com.kennethss.movie.domain.movie.movie.Movie

data class MovieData(
    val id: Int,
    val title: String,
    val rating: Float,
    val overview: String,
    val runtime: Int,
    val posterUrl: String,
    val releaseDate: String,
    val actors: List<ActorData>,
    val directors: List<DirectorData>,
    val previews: List<PreviewData>
) {
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
}

fun MovieData.toDomain() = Movie(
    id = id,
    title = title,
    rating = rating,
    overview = overview,
    runtime = runtime,
    posterUrl = posterUrl,
    releaseDate = releaseDate,
    actors = actors.map { actor -> actor.toDomain() },
    directors = directors.map { director -> director.toDomain() },
    previews = previews.map { preview -> preview.toDomain() },
)