package es.fjruiz.magictimer.ui.screens.main

import es.fjruiz.magictimer.ui.base.BaseIntent

sealed class MainIntent: BaseIntent {
    data object OnInit: MainIntent()
}