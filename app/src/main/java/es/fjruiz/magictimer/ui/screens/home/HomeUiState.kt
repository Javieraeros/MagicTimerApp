package es.fjruiz.magictimer.ui.screens.home

import es.fjruiz.magictimer.ui.vo.SettingsVO

sealed class HomeUiState {
    data object Loading : HomeUiState()
    data class NewGame(val settings: SettingsVO) : HomeUiState()
    data object ContinueGame : HomeUiState()
}
