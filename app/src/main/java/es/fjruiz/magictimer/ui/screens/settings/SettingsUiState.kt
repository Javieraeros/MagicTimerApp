package es.fjruiz.magictimer.ui.screens.settings

import es.fjruiz.magictimer.ui.vo.SettingsVO

sealed class SettingsUiState {
    data object Loading: SettingsUiState()
    data class Success(val settingsVO: SettingsVO): SettingsUiState()
    data class Error(val errorType: SettingsError): SettingsUiState()
}

sealed class SettingsError {
    data object InvalidTime: SettingsError()
    data object InvalidExtraTime: SettingsError()
    data object InvalidAlertTime: SettingsError()
    data object InvalidPlayerNumber: SettingsError()
}