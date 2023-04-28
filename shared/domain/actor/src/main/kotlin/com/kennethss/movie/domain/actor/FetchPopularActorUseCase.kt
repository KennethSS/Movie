package com.kennethss.movie.domain.actor

import com.kennethss.movie.core.di.IoDispatcher
import com.kennethss.movie.core.usecase.FlowUseCase
import com.kennethss.movie.core.usecase.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchPopularActorUseCase @Inject constructor(
    private val actorRepository: ActorRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : FlowUseCase<Int, List<Actor>>(dispatcher) {

    override fun execute(parameters: Int): Flow<Result<List<Actor>>> {
        return actorRepository.getPopularActor(parameters).map {
            Result.Success(it)
        }
    }

}