package com.kennethss.movie.data

import com.kennethss.movie.data.actor.ActorData

interface MovieDbRemoteDataSource {
    suspend fun getMovieDetail(id: Int): MovieData
    suspend fun getPopularMovie(page: Int): List<MovieThumbnailData>
    suspend fun getPopularPerson(page: Int): List<ActorData>
}