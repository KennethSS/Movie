package com.kennethss.movie.remote.response.popular

import com.kennethss.movie.data.movie.MovieThumbnailData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("results")
data class MoviePopularItemResponse(
    @SerialName("adult")
    val adult: Boolean = false,
    @SerialName("backdrop_path")
    val backdropPath: String = "",
    @SerialName("genre_ids")
    val genreIds: List<Int> = listOf(),
    @SerialName("id")
    val id: Int = 0,
    @SerialName("original_language")
    val originalLanguage: String = "",
    @SerialName("original_title")
    val originalTitle: String = "",
    @SerialName("overview")
    val overview: String = "",
    @SerialName("popularity")
    val popularity: Double = 0.0,
    @SerialName("poster_path")
    val posterPath: String = "",
    @SerialName("release_date")
    val releaseDate: String = "",
    @SerialName("title")
    val title: String = "",
    @SerialName("video")
    val video: Boolean = false,
    @SerialName("vote_average")
    val voteAverage: Double = 0.0,
    @SerialName("vote_count")
    val voteCount: Int = 0
)

fun MoviePopularItemResponse.toData() = MovieThumbnailData(
    id = id,
    name = title,
    posterUrl = IMAGE_BASE_HOST + posterPath
)

const val IMAGE_BASE_HOST = "https://image.tmdb.org/t/p/w500"
