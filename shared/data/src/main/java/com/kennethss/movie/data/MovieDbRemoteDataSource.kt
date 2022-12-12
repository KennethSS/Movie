package com.kennethss.movie.data

interface MovieDbRemoteDataSource {
    suspend fun getMovieDetail(id: Int): MovieData
    suspend fun getPopularMovie(): List<MovieThumbnailData>
}