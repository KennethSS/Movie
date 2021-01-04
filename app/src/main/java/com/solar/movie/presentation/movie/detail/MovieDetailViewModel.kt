package com.solar.movie.presentation.movie.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.solar.movie.NetworkState
import com.solar.movie.domain.repository.model.Movie
import com.solar.movie.domain.repository.usecase.MovieUseCase
import com.solar.movie.extension.liveDataScope

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
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    private val _movieDetailLiveData = MutableLiveData<Int>()
    val movieDetailLiveData: LiveData<NetworkState<MovieDetailView>>

    init {
        movieDetailLiveData = _movieDetailLiveData.switchMap { id ->
            liveDataScope(
                networkCall = {
                    movieUseCase.getMovieById(id)
                }, map = { mapToMovieDetailView(it) }
            )
        }
    }

    fun getMovieDetail(id: Int) {
        _movieDetailLiveData.value = id
    }

    private fun mapToMovieDetailView(model: Movie): MovieDetailView {
        return MovieDetailView(
            title = model.title,
            desc = model.desc,
            poster = model.poster
        )
    }
}