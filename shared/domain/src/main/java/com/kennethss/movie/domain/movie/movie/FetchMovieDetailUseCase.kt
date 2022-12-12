package com.kennethss.movie.domain.movie.movie

import com.kennethss.movie.domain.movie.di.IoDispatcher
import com.kennethss.movie.domain.movie.usecase.FlowUseCase
import com.kennethss.movie.domain.movie.usecase.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchMovieDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : FlowUseCase<FetchMovieDetailUseCaseParam, Movie>(dispatcher) {

    override fun execute(parameters: FetchMovieDetailUseCaseParam): Flow<Result<Movie>> {
        return movieRepository.getMovieDetail(parameters.id)
    }
}

data class FetchMovieDetailUseCaseParam(
    val id: Int
)
