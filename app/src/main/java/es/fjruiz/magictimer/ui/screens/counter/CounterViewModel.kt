package es.fjruiz.magictimer.ui.screens.counter

import androidx.lifecycle.viewModelScope
import es.fjruiz.magictimer.navigation.Destination
import es.fjruiz.magictimer.navigation.navigator.Navigator
import es.fjruiz.magictimer.ui.component.card.CounterCardModel
import es.fjruiz.magictimer.ui.component.card.StarEnum
import es.fjruiz.magictimer.ui.component.card.ajani
import es.fjruiz.magictimer.ui.component.card.chandra
import es.fjruiz.magictimer.ui.component.card.jace
import es.fjruiz.magictimer.ui.component.card.vivien
import es.fjruiz.magictimer.ui.base.BaseViewModel
import es.fjruiz.magictimer.ui.screens.counter.CounterUiState.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CounterViewModel(private val navigator: Navigator): BaseViewModel<CounterIntent>() {

    private var _uiState: MutableStateFlow<CounterUiState> = MutableStateFlow(CounterUiState.Loading)

    val uiState: StateFlow<CounterUiState> = _uiState
    override fun handleIntent(intent: CounterIntent) {
        when (intent) {
            CounterIntent.OnInit -> {
                _uiState.update {
                    Success(
                        listOf(
                            CounterCardModel(time = "2:00", image = jace, hasTurn = true, hasPriority = true, stars = StarEnum.TWO),
                            CounterCardModel(time = "2:00", image = vivien, hasTurn = false, hasPriority = false, stars = StarEnum.ONE),
                            // CounterCardModel(time = "2:00", image = chandra, hasTurn = false, hasPriority = false, stars = StarEnum.NONE),
                            // CounterCardModel(time = "2:00", image = ajani, hasTurn = false, hasPriority = false, stars = StarEnum.TWO)
                        )
                    )
                }
                // TODO: Use case to get last game from database if needed
            }
            CounterIntent.OnPauseClicked -> _uiState.update {
                Pause // TODO: Change for an ID to show localized message
            }
            is CounterIntent.OnUserClicked -> _uiState.update {
                it
                // TODO: Use case to update database
            }

            CounterIntent.OnPauseDismissed -> _uiState.update {
                it
                // TODO: Use case
            }

            is CounterIntent.OnTakePriorityClicked -> _uiState.update {
                it
                // TODO: 27/10/25 Use case
            }
            is CounterIntent.OnTakeTurnClicked -> _uiState.update {
                it
                // TODO: 27/10/25 Use case
            }

            is CounterIntent.OnSettingsClicked -> handleOnSettingsClicked()
        }
    }

    private fun handleOnSettingsClicked() {
        viewModelScope.launch {
            navigator.navigateTo(Destination.Settings)
        }
    }

}