package com.kennethss.movie.feature.movie.ui.top

import com.kennethss.movie.core.mvi.UiEffect
import com.kennethss.movie.core.mvi.UiEvent
import com.kennethss.movie.core.mvi.UiState

interface MovieTopContentContract {
    sealed interface Event : UiEvent

    sealed interface State : UiState {
        object Loading : State

        companion object {
            fun initialState() = Loading
        }
    }

    sealed interface Effect : UiEffect
}