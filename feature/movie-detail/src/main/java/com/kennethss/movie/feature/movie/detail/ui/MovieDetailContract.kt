package com.kennethss.movie.feature.movie.detail.ui

import com.kennethss.movie.core.mvi.UiEffect
import com.kennethss.movie.core.mvi.UiEvent
import com.kennethss.movie.core.mvi.UiState
import com.kennethss.movie.domain.movie.movie.Movie

interface MovieDetailContract {
    sealed interface Event : UiEvent

    sealed interface State : UiState {
        object Loading : State
        data class Success(
            val movie: Movie
        ) : State

        companion object {
            fun initialState() = Loading
        }
    }

    sealed interface Effect : UiEffect {
        data class ShowErrorDialog(val message: String) : Effect
    }
}