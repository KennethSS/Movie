package com.kennethss.movie.data.movie

import com.kennethss.movie.domain.movie.movie.Movie
import com.kennethss.movie.domain.movie.movie.MovieRepository
import com.kennethss.movie.domain.movie.movie.MovieThumbnail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieDbRemoteDataSource: MovieDbRemoteDataSource
) : MovieRepository {

    override fun getMovieDetail(id: Int): Flow<Movie> = flow{
        emit(movieDbRemoteDataSource.getMovieDetail(id).toDomain())
    }

    override fun getPopularMovies(page: Int): Flow<List<MovieThumbnail>> = flow {
        emit(movieDbRemoteDataSource.getPopularMovie(page).map { it.toDomain() })
    }
}