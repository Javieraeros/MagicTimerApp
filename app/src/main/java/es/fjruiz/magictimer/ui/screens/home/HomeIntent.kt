package es.fjruiz.magictimer.ui.screens.home

import es.fjruiz.magictimer.ui.base.BaseIntent

sealed class HomeIntent : BaseIntent {
    data object OnInit : HomeIntent()
    data object OnStartNewGame : HomeIntent()
    data object OnContinueGame : HomeIntent()
}
