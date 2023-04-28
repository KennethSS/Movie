package com.kennethss.movie.data.actor

import com.kennethss.movie.domain.actor.Actor
import com.kennethss.movie.domain.actor.ActorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ActorRepositoryImpl(
    private val actorRemoteDataSource: ActorRemoteDataSource
) : ActorRepository {

    override fun getPopularActor(page: Int): Flow<List<Actor>> = flow {
        val result = actorRemoteDataSource.getPopularPerson(page).map { it.toDomain() }
        emit(result)
    }
}