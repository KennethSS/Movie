package com.solar.movie.data.local

import com.solar.movie.data.entity.MovieEntity
import com.solar.movie.domain.repository.model.Movie

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
interface MovieLocal {
    suspend fun setMovie(movie: MovieEntity)
    suspend fun deleteMovie(id: Int)
    suspend fun getFavoriteMovie(id: Int): MovieEntity
    suspend fun getFavoriteMovieList(): List<MovieEntity>
}