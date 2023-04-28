package com.kennethss.movie.domain.actor

import kotlinx.coroutines.flow.Flow

interface ActorRepository {
    fun getPopularActor(page: Int): Flow<List<Actor>>
}