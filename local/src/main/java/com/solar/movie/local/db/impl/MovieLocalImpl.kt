package com.solar.movie.local.db.impl

import com.solar.movie.data.entity.MovieEntity
import com.solar.movie.data.local.MovieLocal
import com.solar.movie.local.db.dao.MovieDao
import com.solar.movie.local.db.mapper.MovieLocalMapper

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
class MovieLocalImpl(
    private val movieDao: MovieDao,
    private val mapper: MovieLocalMapper) : MovieLocal {

    override suspend fun setMovie(movie: MovieEntity) {
        movieDao.insert(mapper.transformMovieEntityToMovieLocalEntity(movie))
    }

    override suspend fun deleteMovie(id: Int) {
        movieDao.deleteById(id)
    }

    override suspend fun getFavoriteMovie(id: Int): MovieEntity {
        return mapper.transformMovieLocalEntityToMovieEntity(movieDao.getFavoriteMovieById(id))
    }

    override suspend fun getFavoriteMovieList(): List<MovieEntity> {
        println("getFavoriteMovieList!")

        return movieDao.getFavoriteMovieList().map { mapper.transformMovieLocalEntityToMovieEntity(it) }
    }
}