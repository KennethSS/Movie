package com.solar.movie.presentation.favorite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solar.movie.domain.repository.usecase.FavoriteUseCase
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
class FavoriteItemViewModel @ViewModelInject constructor(
        private val favoriteUseCase: FavoriteUseCase
) : ViewModel() {

    private val _deleteFavoriteLiveData by lazy { MutableLiveData<FavoriteMovieView>() }
    val deleteFavoriteLiveData: LiveData<FavoriteMovieView>
        get() = _deleteFavoriteLiveData

    fun deleteFavoriteMovie(item: FavoriteMovieView) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                favoriteUseCase.deleteFavorite(item.id)
            }.onSuccess {
                _deleteFavoriteLiveData.postValue(item)
            }
        }
    }
}