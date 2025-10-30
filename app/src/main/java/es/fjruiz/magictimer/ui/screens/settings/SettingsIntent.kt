package es.fjruiz.magictimer.ui.screens.settings

import es.fjruiz.magictimer.ui.base.BaseIntent
import es.fjruiz.magictimer.ui.vo.ConfigVO

sealed class SettingsIntent: BaseIntent {
    data object OnInit: SettingsIntent()
    data object OnNewGameClicked: SettingsIntent()
    data object OnCloseClicked: SettingsIntent()
    data object OnCloseErrorClicked: SettingsIntent()
    data object OnConfirmNewGame: SettingsIntent()
    data object OnCancelNewGame: SettingsIntent()

    data class Save(
        val config: ConfigVO
    ): SettingsIntent()
}