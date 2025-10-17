package es.fjruiz.magictimer.ui.screens.splash

import es.fjruiz.magictimer.ui.base.BaseIntent

sealed class SplashIntent: BaseIntent {
    object NavigateNext: SplashIntent()
}