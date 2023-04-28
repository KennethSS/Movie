package com.kennethss.movie.domain.movie.movie

import com.kennethss.movie.core.di.IoDispatcher
import com.kennethss.movie.core.usecase.FlowUseCase
import com.kennethss.movie.core.usecase.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchMovieDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : FlowUseCase<FetchMovieDetailUseCaseParam, Movie>(dispatcher) {

    override fun execute(parameters: FetchMovieDetailUseCaseParam): Flow<Result<Movie>> {
        return movieRepository.getMovieDetail(parameters.id).map { Result.Success(it) }
    }
}

data class FetchMovieDetailUseCaseParam(
    val id: Int
)
