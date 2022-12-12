package com.kennethss.movie.remote.moviedb

import com.kennethss.movie.data.MovieData
import com.kennethss.movie.data.MovieThumbnailData
import com.kennethss.movie.data.MovieDbRemoteDataSource
import com.kennethss.movie.remote.response.detail.toData
import com.kennethss.movie.remote.response.popular.toData
import com.kennethss.movie.remote.service.MovieDbService
import javax.inject.Inject

class MovieDbRemoteDataSourceImpl @Inject constructor(
    private val movieDbService: MovieDbService
) : MovieDbRemoteDataSource {

    override suspend fun getMovieDetail(id: Int): MovieData {
        return movieDbService.getMovieDetail(id).toData()
    }

    override suspend fun getPopularMovie(): List<MovieThumbnailData> {
        return movieDbService.getPopularMovie().results.map { it.toData() }
    }
}