package com.kennethss.movie.core.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MviReducer<Event : UiEvent, State : UiState, Effect : UiEffect>(
    private val viewModelScope: CoroutineScope,
    initialState: State,
    private val handleEvent: (Event) -> Unit,
) {
    private val _stateFlow = MutableStateFlow(initialState)
    val stateFlow = _stateFlow.asStateFlow()

    private val _eventFlow = MutableSharedFlow<Event>()

    private val _effectFlow = Channel<Effect>()
    val effectFlow = _effectFlow.receiveAsFlow()

    init {
        handleEvent()
    }

    private fun handleEvent() {
        _eventFlow
            .onEach { event -> handleEvent(event) }
            .launchIn(viewModelScope)
    }

    fun setState(reduce: suspend State.() -> State) {
        viewModelScope.launch {
            _stateFlow.emit(_stateFlow.value.reduce())
        }
    }

    fun setEvent(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    fun setEffect(effect: Effect) {
        viewModelScope.launch {
            _effectFlow.send(effect)
        }
    }
}