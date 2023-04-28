package com.kennethss.movie.data.movie

interface MovieDbRemoteDataSource {
    suspend fun getMovieDetail(id: Int): MovieData
    suspend fun getPopularMovie(page: Int): List<MovieThumbnailData>
}