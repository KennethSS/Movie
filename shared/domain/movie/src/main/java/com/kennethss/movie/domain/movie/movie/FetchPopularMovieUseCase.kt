package com.kennethss.movie.domain.movie.movie

import com.kennethss.movie.core.di.IoDispatcher
import com.kennethss.movie.core.usecase.FlowUseCase
import com.kennethss.movie.core.usecase.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchPopularMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : FlowUseCase<FetchPopularMovieUseCaseParam, List<MovieThumbnail>>(dispatcher) {

    override fun execute(parameters: FetchPopularMovieUseCaseParam) =
        movieRepository.getPopularMovies(parameters.page)
            .map { Result.Success(it) }
}

data class FetchPopularMovieUseCaseParam(val page: Int)