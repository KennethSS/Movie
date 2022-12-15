package com.kennethss.movie.domain.movie.movie

import com.kennethss.movie.domain.movie.usecase.Result
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovieDetail(id: Int): Flow<Result<Movie>>
    fun getPopularMovies(page: Int): Flow<Result<List<MovieThumbnail>>>
}