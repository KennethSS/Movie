package com.kennethss.movie.data.actor

interface ActorRemoteDataSource {
    suspend fun getPopularPerson(page: Int): List<ActorData>
}