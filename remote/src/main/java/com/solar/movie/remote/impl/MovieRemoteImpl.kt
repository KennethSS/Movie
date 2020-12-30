package com.solar.movie.remote.impl

import com.solar.movie.data.entity.MovieEntity
import com.solar.movie.data.remote.MovieRemote
import com.solar.movie.remote.response.mapper.MovieDetailMapper
import com.solar.movie.remote.service.MovieService

/**
 * Copyright 2020 Kenneth
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **/
class MovieRemoteImpl(
    private val service: MovieService,
    private val mapper: MovieDetailMapper
) : MovieRemote {
    override suspend fun getMovieDetailById(id: Int): MovieEntity {
        return mapper.transformResponseToEntity(service.getMovieDetailByIdFromCoroutine())
    }
}