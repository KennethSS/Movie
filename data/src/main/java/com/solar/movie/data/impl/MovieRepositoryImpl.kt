package com.solar.movie.data.impl

import com.solar.movie.data.entity.mapper.MovieEntityMapper
import com.solar.movie.data.local.MovieLocal
import com.solar.movie.data.remote.MovieRemote
import com.solar.movie.domain.repository.model.Movie
import com.solar.movie.domain.repository.repository.MovieRepository

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
class MovieRepositoryImpl(
    private val service: MovieRemote,
    private val local: MovieLocal,
    private val mapper: MovieEntityMapper
) : MovieRepository {

    override suspend fun setFavoriteMovie(movie: Movie) {
        val entity = mapper.transformMovieToMovieEntity(movie)
        local.setMovie(entity)
    }

    override suspend fun deleteFavoriteMovie(id: Int) {
        local.deleteMovie(id)
    }

    override suspend fun getMovieById(id: Int): Movie {
        return mapper.transformEntityToModel(service.getMovieDetailById(id))
    }

    override suspend fun getFavoriteMovieList(): List<Movie> {
        println("getFavoriteMovieList data")
        return local.getFavoriteMovieList().map { mapper.transformEntityToModel(it) }
    }

    override suspend fun getPopularMovie(): List<Movie> {
        return service.getPopularMovie().map {
            mapper.transformEntityToModel(it)
        }
    }

    override suspend fun getFavoriteMovie(id: Int): Movie {
        return mapper.transformEntityToModel(local.getFavoriteMovie(id))
    }
}