package com.kennethss.movie.remote.response.popular

import com.kennethss.movie.data.actor.ActorData

data class PersonPopularResponse(
    val page: Int,
    val results: List<Person>,
    val total_pages: Int,
    val total_results: Int
) {
    data class Person(
        val adult: Boolean,
        val gender: Int,
        val id: Int,
        val known_for: List<KnownFor>,
        val known_for_department: String,
        val name: String,
        val popularity: Double,
        val profile_path: String
    ) {
        data class KnownFor(
            val adult: Boolean,
            val backdrop_path: String,
            val first_air_date: String,
            val genre_ids: List<Int>,
            val id: Int,
            val media_type: String,
            val name: String,
            val origin_country: List<String>,
            val original_language: String,
            val original_name: String,
            val original_title: String,
            val overview: String,
            val poster_path: String,
            val release_date: String,
            val title: String,
            val video: Boolean,
            val vote_average: Double,
            val vote_count: Int
        )

        companion object {
            fun Person.toActorData(): ActorData {
                return ActorData(
                    id = id,
                    name = name,
                    profileUrl = IMAGE_BASE_HOST + profile_path,
                    character = ""
                )
            }
        }
    }
}