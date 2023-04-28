package com.kennethss.movie.feature.movie.detail.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kennethss.movie.core.mvi.MviReducer
import com.kennethss.movie.core.usecase.onError
import com.kennethss.movie.core.usecase.onSuccess
import com.kennethss.movie.domain.movie.movie.FetchMovieDetailUseCase
import com.kennethss.movie.domain.movie.movie.FetchMovieDetailUseCaseParam
import com.kennethss.movie.feature.movie.detail.navigation.KEY_MOVIE_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val fetchMovieDetailUseCase: FetchMovieDetailUseCase
) : ViewModel() {

    private val movieId: Int = checkNotNull(savedStateHandle[KEY_MOVIE_ID])

    private val reducer =
        MviReducer<MovieDetailContract.Event, MovieDetailContract.State, MovieDetailContract.Effect>(
            viewModelScope = viewModelScope,
            initialState = MovieDetailContract.State.initialState(),
            handleEvent = ::handleEvent
        )

    val eventHandler = reducer::setEvent
    val stateFlow = reducer.stateFlow
    val effectFlow = reducer.effectFlow

    init {
        fetchMovieDetailUseCase(FetchMovieDetailUseCaseParam(movieId))
            .onSuccess { movie ->
                reducer.setState { MovieDetailContract.State.Success(movie) }
            }
            .onError {
                //Todo 에러 처리
            }
            .launchIn(viewModelScope)
    }

    private fun handleEvent(event: MovieDetailContract.Event) {

    }
}