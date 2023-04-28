package com.kennethss.movie.remote.moviedb

import com.kennethss.movie.data.movie.MovieData
import com.kennethss.movie.data.movie.MovieThumbnailData
import com.kennethss.movie.data.movie.MovieDbRemoteDataSource
import com.kennethss.movie.remote.response.detail.toData
import com.kennethss.movie.remote.response.popular.toData
import com.kennethss.movie.remote.service.MovieDbService
import javax.inject.Inject

class MovieDbRemoteDataSourceImpl @Inject constructor(
    private val movieDbService: MovieDbService
) : com.kennethss.movie.data.movie.MovieDbRemoteDataSource {

    override suspend fun getMovieDetail(id: Int): com.kennethss.movie.data.movie.MovieData {
        return movieDbService.getMovieDetail(id).toData()
    }

    override suspend fun getPopularMovie(page: Int): List<com.kennethss.movie.data.movie.MovieThumbnailData> {
        return movieDbService.getPopularMovie(page).results.map { it.toData() }
    }
}