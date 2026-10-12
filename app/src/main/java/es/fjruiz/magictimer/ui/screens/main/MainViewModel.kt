package es.fjruiz.magictimer.ui.screens.main

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.fjruiz.domain.usecase.game.IsActiveGameUC
import es.fjruiz.magictimer.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val isActiveGameUC: IsActiveGameUC
): BaseViewModel<MainIntent>() {

    private var _uiState: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState.Loading)
    val uiState: StateFlow<MainUiState> = _uiState

    override fun handleIntent(intent: MainIntent) {
        when (intent) {
            MainIntent.OnInit -> handleOnInit()
        }
    }


    private fun handleOnInit() {
        viewModelScope.launch(Dispatchers.IO) {
            val state = if(isActiveGameUC()) {
                MainUiState.RenderContinueGame
            } else {
                MainUiState.RenderNewGame
            }

            _uiState.value = state
        }
    }
}