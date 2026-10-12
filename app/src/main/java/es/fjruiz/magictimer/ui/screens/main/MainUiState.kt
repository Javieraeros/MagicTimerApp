package es.fjruiz.magictimer.ui.screens.main

import es.fjruiz.magictimer.ui.base.BaseIntent

sealed class MainUiState {
    data object Loading: MainUiState()
    data object RenderNewGame: MainUiState()
    data object RenderContinueGame: MainUiState()
}