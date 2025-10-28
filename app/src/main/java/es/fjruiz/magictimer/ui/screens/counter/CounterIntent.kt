package es.fjruiz.magictimer.ui.screens.counter

import es.fjruiz.magictimer.ui.base.BaseIntent

sealed class CounterIntent() : BaseIntent {
    object OnInit : CounterIntent()

    data class OnUserClicked(val playerId: String) : CounterIntent()
    data object OnPauseClicked : CounterIntent()
    data object OnPauseDismissed: CounterIntent()
    data object OnSettingsClicked : CounterIntent()

    data class OnTakePriorityClicked(val playerId: String): CounterIntent()
    data class OnTakeTurnClicked(val playerId: String): CounterIntent()
}
