package com.kennethss.movie.domain.movie.movie

import com.kennethss.movie.domain.movie.di.IoDispatcher
import com.kennethss.movie.domain.movie.usecase.NoParamFlowUseCase
import com.kennethss.movie.domain.movie.usecase.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class FetchPopularMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : NoParamFlowUseCase<List<MovieThumbnail>>(dispatcher) {

    override fun execute(): Flow<Result<List<MovieThumbnail>>> = movieRepository.getPopularMovies()
        .onStart { emit(Result.Loading) }
}