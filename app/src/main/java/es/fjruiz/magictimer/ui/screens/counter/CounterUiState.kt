package es.fjruiz.magictimer.ui.screens.counter

import es.fjruiz.magictimer.ui.component.card.CounterCardModel

sealed class CounterUiState {
    data class Success(
        val counterCardModels: List<CounterCardModel>
    ): CounterUiState()

    data object Pause: CounterUiState()

    data object Loading: CounterUiState()
}