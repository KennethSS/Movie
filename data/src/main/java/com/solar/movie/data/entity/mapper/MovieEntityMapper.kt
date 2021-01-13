package com.solar.movie.data.entity.mapper

import com.solar.movie.data.entity.ActorEntity
import com.solar.movie.data.entity.MovieEntity
import com.solar.movie.domain.repository.model.Actor
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
class MovieEntityMapper {
    fun transformEntityToModel(entity: MovieEntity) = entity.run {
        Movie(
                id = entity.id,
                title = entity.title,
                desc = entity.desc,
                poster = entity.poster,
                releaseDate = entity.releaseDate,
                actors = entity.actors.map(::transformActorEntityToActor),
                backdrops = entity.backdrops
        )
    }

    fun transformMovieToMovieEntity(movie: Movie) = movie.run {
        MovieEntity(
            id = id,
            title = title,
            desc = desc,
            poster = poster,
            releaseDate = releaseDate,
            actors = actors.map(::transformActorToActorEntity),
            backdrops = backdrops
        )
    }

    private fun transformActorEntityToActor(entity: ActorEntity) = entity.run {
        Actor(
                name = name,
                profile = profile,
                character = character
        )
    }

    private fun transformActorToActorEntity(actor: Actor) = actor.run {
        ActorEntity(
            name = name,
            profile = profile,
            character = character
        )
    }
}