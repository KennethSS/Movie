package com.kennethss.movie.data.actor

import com.kennethss.movie.data.MovieDbRemoteDataSource
import com.kennethss.movie.domain.movie.actor.Actor
import com.kennethss.movie.domain.movie.actor.ActorRepository
import com.kennethss.movie.domain.movie.usecase.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ActorRepositoryImpl(
    private val movieDbRemoteDataSource: MovieDbRemoteDataSource
) : ActorRepository {

    override fun getPopularActor(page: Int): Flow<Result<List<Actor>>> = flow {
        emit(Result.Loading)
        val result = movieDbRemoteDataSource.getPopularPerson(page).map { it.toDomain() }
        emit(Result.Success(result))
    }
}