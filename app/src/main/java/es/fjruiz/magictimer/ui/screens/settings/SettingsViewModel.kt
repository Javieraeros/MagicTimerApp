package es.fjruiz.magictimer.ui.screens.settings

import androidx.lifecycle.viewModelScope
import es.fjruiz.domain.usecase.GetConfigUC
import es.fjruiz.domain.usecase.UpdateConfigUC
import es.fjruiz.domain.usecase.game.CreateGameUC
import es.fjruiz.magictimer.navigation.navigator.Navigator
import es.fjruiz.magictimer.ui.base.BaseViewModel
import es.fjruiz.magictimer.ui.mapper.toModel
import es.fjruiz.magictimer.ui.mapper.toVO
import es.fjruiz.magictimer.ui.vo.ConfigVO
import es.fjruiz.magictimer.ui.vo.SettingsVO
import es.fjruiz.magictimer.utils.InvalidAlertTimeException
import es.fjruiz.magictimer.utils.InvalidExtraTimeException
import es.fjruiz.magictimer.utils.InvalidPlayerNumberException
import es.fjruiz.magictimer.utils.InvalidTimeException
import es.fjruiz.magictimer.utils.SettingException
import es.fjruiz.magictimer.utils.isValid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsViewModel(
    private val navigator: Navigator,
    private val getConfigUC: GetConfigUC,
    private val updateConfigUC: UpdateConfigUC,
    private val createGameUC: CreateGameUC
): BaseViewModel<SettingsIntent>() {

    private val _uiState: MutableStateFlow<SettingsUiState> = MutableStateFlow(SettingsUiState.Loading)
    val uiState: StateFlow<SettingsUiState> = _uiState

    private var lastConfig: ConfigVO? = null

    override fun handleIntent(intent: SettingsIntent) {
        when (intent) {
            SettingsIntent.OnInit -> onInit()
            SettingsIntent.OnCloseClicked -> onClose()
            SettingsIntent.OnNewGameClicked -> onNewGame()
            is SettingsIntent.Save -> onSaveSettings(intent.config)
            SettingsIntent.OnConfirmNewGame -> onConfirmNewGame()
            SettingsIntent.OnCancelNewGame -> onIgnoreNewGame()
            SettingsIntent.OnCloseErrorClicked -> onCloseError()
        }
    }

    private fun onInit() {
        viewModelScope.launch(Dispatchers.IO) {
            val settings = SettingsVO(getConfigUC().toVO(), false)
            _uiState.value = SettingsUiState.Success(settings)
        }

    }

    private fun onClose() {
        navigator.navigateBack()
    }

    private fun onNewGame() {
        _uiState.update {
            val successState = it as? SettingsUiState.Success
            val newGameState = successState?.copy(settingsVO = successState.settingsVO.copy(showNewGameConfirmation = true))

            newGameState ?: it
        }
    }

    private fun onSaveSettings(configVO: ConfigVO) {
        lastConfig = configVO

        val isValid = try {
            isValid(configVO)
        } catch (exception: SettingException) {
            showError(exception)
            false
        }

        if (isValid) {
            viewModelScope.launch(Dispatchers.IO) {
                updateConfigUC(configVO.toModel())
                lastConfig = null

                val settings = SettingsVO(getConfigUC().toVO(),
                    showNewGameConfirmation = false,
                    showSavedCorrectly = true
                )
                _uiState.value = SettingsUiState.Success(settings)
            }
        }
    }

    private fun onConfirmNewGame() {
        viewModelScope.launch(Dispatchers.IO) {
            val config = getConfigUC()
            createGameUC(config)
            _uiState.value = SettingsUiState.Success(SettingsVO(config.toVO(), false))
            withContext(Dispatchers.Main) {
                navigator.navigateBack()
            }
        }
    }

    private fun onIgnoreNewGame() {
        _uiState.update {
            val successState = it as? SettingsUiState.Success
            val newGameState = successState?.copy(settingsVO = successState.settingsVO.copy(showNewGameConfirmation = false))

            newGameState ?: it
        }
    }

    private fun onCloseError() {
        viewModelScope.launch(Dispatchers.IO) {
            val config = lastConfig ?: getConfigUC().toVO()
            _uiState.update {
                SettingsUiState.Success(SettingsVO(config, false))
            }
        }
    }

    private fun showError(exception: SettingException) {
        val settingsError = when (exception) {
            is InvalidTimeException -> SettingsError.InvalidTime
            is InvalidExtraTimeException -> SettingsError.InvalidExtraTime
            is InvalidPlayerNumberException -> SettingsError.InvalidPlayerNumber
            is InvalidAlertTimeException -> SettingsError.InvalidAlertTime
        }

        _uiState.update {
            SettingsUiState.Error(settingsError)
        }
    }
}