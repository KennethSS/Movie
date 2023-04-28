package com.kennethss.movie.domain.movie.actor

import kotlinx.coroutines.flow.Flow
import com.kennethss.movie.domain.movie.usecase.Result

interface ActorRepository {
    fun getPopularActor(page: Int): Flow<Result<List<Actor>>>
}