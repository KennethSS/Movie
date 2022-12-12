package com.kennethss.movie.data

import com.kennethss.movie.domain.movie.movie.Movie
import com.kennethss.movie.domain.movie.movie.MovieThumbnail
import com.kennethss.movie.domain.movie.movie.MovieRepository
import com.kennethss.movie.domain.movie.usecase.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieDbRemoteDataSource: MovieDbRemoteDataSource
) : MovieRepository {

    override fun getMovieDetail(id: Int): Flow<Result<Movie>> = flow{
        emit(Result.Loading)
        val result = movieDbRemoteDataSource.getMovieDetail(id).toDomain()
        emit(Result.Success(result))
    }

    override fun getPopularMovies(): Flow<Result<List<MovieThumbnail>>> = flow {
        emit(Result.Loading)
        val result = movieDbRemoteDataSource.getPopularMovie().map { it.toDomain() }
        emit(Result.Success(result))
    }
}