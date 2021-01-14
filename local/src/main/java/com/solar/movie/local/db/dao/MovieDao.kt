package com.solar.movie.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.solar.movie.local.db.entity.MovieLocalEntity

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
@Dao
interface MovieDao : BaseDao<MovieLocalEntity> {
    @Query("SELECT * FROM MovieLocalEntity")
    suspend fun getFavoriteMovieList() : List<MovieLocalEntity>

    @Query("SELECT * FROM MovieLocalEntity where id = :id")
    suspend fun getFavoriteMovieById(id: Int) : MovieLocalEntity

    @Query("DELETE FROM MovieLocalEntity WHERE id = :id")
    suspend fun deleteById(id: Int)
}