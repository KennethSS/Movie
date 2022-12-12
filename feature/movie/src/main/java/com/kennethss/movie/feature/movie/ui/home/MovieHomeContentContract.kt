package com.kennethss.movie.feature.movie.ui.home

import com.kennethss.movie.core.mvi.UiEffect
import com.kennethss.movie.core.mvi.UiEvent
import com.kennethss.movie.core.mvi.UiState
import com.kennethss.movie.domain.movie.movie.MovieThumbnail

interface MovieHomeContentContract {
    sealed interface Event : UiEvent

    sealed interface State : UiState {
        object Loading : State
        data class Success(
            val movies: List<MovieThumbnail>
        ) : State

        companion object {
            fun initialState() = Loading
        }
    }

    sealed interface Effect : UiEffect {
        data class ShowErrorDialog(val message: String) : Effect
    }
}