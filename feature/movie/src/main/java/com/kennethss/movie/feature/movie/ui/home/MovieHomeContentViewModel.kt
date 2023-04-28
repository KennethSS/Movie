package com.kennethss.movie.feature.movie.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kennethss.movie.core.mvi.MviReducer
import com.kennethss.movie.core.usecase.onError
import com.kennethss.movie.core.usecase.onSuccess
import com.kennethss.movie.domain.movie.movie.FetchPopularMovieUseCase
import com.kennethss.movie.domain.movie.movie.FetchPopularMovieUseCaseParam
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
internal class MovieHomeContentViewModel @Inject constructor(
    fetchPopularMovieUseCase: FetchPopularMovieUseCase
) : ViewModel() {

    private val reducer =
        MviReducer<MovieHomeContentContract.Event, MovieHomeContentContract.State, MovieHomeContentContract.Effect>(
            viewModelScope = viewModelScope,
            initialState = MovieHomeContentContract.State.initialState(),
            handleEvent = ::handleEvent
        )

    val eventHandler = reducer::setEvent
    val stateFlow = reducer.stateFlow
    val effectFlow = reducer.effectFlow

    init {
        fetchPopularMovieUseCase(FetchPopularMovieUseCaseParam(1))
            .onSuccess { reducer.setState { MovieHomeContentContract.State.Success(it) } }
            .onError { }
            .launchIn(viewModelScope)
    }

    private fun handleEvent(event: MovieHomeContentContract.Event) {

    }
}