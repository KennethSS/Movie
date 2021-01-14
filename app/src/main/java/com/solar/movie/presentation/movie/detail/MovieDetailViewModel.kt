package com.solar.movie.presentation.movie.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.solar.movie.NetworkState
import com.solar.movie.domain.repository.model.Actor
import com.solar.movie.domain.repository.model.Movie
import com.solar.movie.domain.repository.usecase.FavoriteUseCase
import com.solar.movie.domain.repository.usecase.MovieUseCase
import com.solar.movie.extension.liveDataScope
import com.solar.movie.presentation.movie.detail.actor.ActorView
import com.solar.movie.presentation.movie.detail.backdrop.BackdropView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

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
class MovieDetailViewModel @ViewModelInject constructor(
        private val movieUseCase: MovieUseCase,
        private val favoriteUseCase: FavoriteUseCase,
) : ViewModel() {

    private val _movieDetailLiveData = MutableLiveData<Int>()
    val movieDetailLiveData: LiveData<NetworkState<MovieDetailView>>

    private val _favoriteState = MutableLiveData<Int>()
    val favoriteState: LiveData<Boolean>

    init {
        movieDetailLiveData = _movieDetailLiveData.switchMap { id ->
            liveDataScope(
                    networkCall = {
                        movieUseCase.getMovieById(id)
                    }, map = { mapToMovieDetailView(it) }
            )
        }

        favoriteState = _favoriteState.switchMap { id ->
            liveData(Dispatchers.IO) {
                emit(false)
                runCatching {
                    favoriteUseCase.getFavoriteMovie(id)
                }.onSuccess {
                    emit(true)
                }.onFailure {
                    emit(false)
                }
            }
        }
    }

    fun getMovieDetail(id: Int) {
        _movieDetailLiveData.value = id
        _favoriteState.value = id
    }

    fun setMovieFavorite(movie: MovieDetailView) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                favoriteUseCase.setFavorite(transformMovieDetailViewToMovie(movie))
            }.onFailure {
                // Todo Error Case
            }.onSuccess {
                _favoriteState.postValue(movie.id)
            }
        }
    }

    private fun transformMovieDetailViewToMovie(model: MovieDetailView) = model.run {
        Movie(
                id = id,
                title = title,
                desc = desc,
                poster = poster,
                actors = listOf(),
                backdrops = listOf(),
                releaseDate = ""
        )
    }

    private fun mapToMovieDetailView(model: Movie) = model.run {
        MovieDetailView(
                id = id,
                title = title,
                desc = desc,
                poster = poster,
                actors = actors.map(::mapToActorView),
                backdrops = backdrops.map(::mapToBackdrop)
        )
    }

    private fun mapToActorView(actor: Actor) = actor.run {
        ActorView(
                name = name,
                profile = profile,
                character = character
        )
    }

    private fun mapToBackdrop(url: String) = BackdropView(url)
}