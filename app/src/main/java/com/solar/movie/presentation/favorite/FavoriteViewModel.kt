package com.solar.movie.presentation.favorite

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.solar.movie.NetworkState
import com.solar.movie.domain.repository.model.Movie
import com.solar.movie.domain.repository.usecase.FavoriteUseCase
import com.solar.movie.extension.liveDataScope
import kotlinx.coroutines.Dispatchers
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
class FavoriteViewModel @ViewModelInject constructor(
    private val favoriteUseCase: FavoriteUseCase
) : ViewModel() {

    private val _favoriteMovieListLiveData = MutableLiveData<NetworkState<List<FavoriteMovieView>>>()
    val favoriteMovieListLiveData: LiveData<NetworkState<List<FavoriteMovieView>>>
        get() = _favoriteMovieListLiveData

    init {
        /*favoriteMovieListLiveData = _favoriteMovieListLiveData.switchMap { id ->
            liveDataScope(
                networkCall = {
                    favoriteUseCase.getFavoriteMovieList()
                }, map = { it.map(::transformMovieToFavoriteMovieView) }
            )
        }*/

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = favoriteUseCase.getFavoriteMovieList()

                Log.d("FavoriteVieWModel", "size ${result.size}")

                val map = NetworkState.Success(result.map(::transformMovieToFavoriteMovieView))
                _favoriteMovieListLiveData.postValue(map)

            } catch (e: Exception) {
                Log.d("FavoriteVieWModel", "Faildget favorite")
            }

        }
    }

    private fun transformMovieToFavoriteMovieView(movie: Movie) = movie.run {
        FavoriteMovieView(
            id = id,
            poster = poster,
            name = title,
            releaseDate = releaseDate
        )
    }
}