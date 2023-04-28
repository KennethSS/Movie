package com.kennethss.movie.domain.movie.movie

import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovieDetail(id: Int): Flow<Movie>
    fun getPopularMovies(page: Int): Flow<List<MovieThumbnail>>
}