package es.fjruiz.magictimer.ui.screens.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.fjruiz.domain.usecase.GetConfigUC
import es.fjruiz.domain.usecase.game.CreateGameUC
import es.fjruiz.domain.usecase.game.IsActiveGameUC
import es.fjruiz.magictimer.navigation.Destination
import es.fjruiz.magictimer.navigation.navigator.Navigator
import es.fjruiz.magictimer.ui.base.BaseViewModel
import es.fjruiz.magictimer.ui.mapper.toVO
import es.fjruiz.magictimer.ui.vo.SettingsVO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val isActiveGameUC: IsActiveGameUC,
    private val createGameUC: CreateGameUC,
    private val getConfigUC: GetConfigUC,
    private val navigator: Navigator
) : BaseViewModel<HomeIntent>() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    override fun handleIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.OnInit -> handleOnInit()
            HomeIntent.OnStartNewGame -> handleOnStartNewGame()
            HomeIntent.OnContinueGame -> handleOnContinueGame()
        }
    }

    private fun handleOnInit() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = if (isActiveGameUC()) {
                HomeUiState.ContinueGame
            } else {
                val settings = SettingsVO(getConfigUC().toVO(), false)
                HomeUiState.NewGame(settings)
            }
        }
    }

    private fun handleOnStartNewGame() {
        viewModelScope.launch(Dispatchers.IO) {
            createGameUC(getConfigUC())
            navigator.navigateTo(Destination.Counter)
        }
    }

    private fun handleOnContinueGame() {
        navigator.navigateTo(Destination.Counter)
    }
}
