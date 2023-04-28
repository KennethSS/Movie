package com.kennethss.movie.remote.actor

import com.kennethss.movie.data.actor.ActorData
import com.kennethss.movie.data.actor.ActorRemoteDataSource
import com.kennethss.movie.remote.response.popular.PersonPopularResponse.Person.Companion.toActorData
import com.kennethss.movie.remote.service.MovieDbService

class ActorRemoteDataSourceImpl(
    private val service: MovieDbService
) : ActorRemoteDataSource {

    override suspend fun getPopularPerson(page: Int): List<ActorData> {
        return service.getPopularPerson(page).results.map { it.toActorData() }
    }

}