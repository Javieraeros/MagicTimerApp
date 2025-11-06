package es.fjruiz.magictimer.ui.screens.counter

import androidx.lifecycle.viewModelScope
import es.fjruiz.domain.usecase.GetConfigUC
import es.fjruiz.domain.usecase.game.CreateGameUC
import es.fjruiz.domain.usecase.game.GetGameUC
import es.fjruiz.domain.usecase.game.GetLastGameUC
import es.fjruiz.domain.usecase.game.IsActiveGameUC
import es.fjruiz.domain.usecase.game.OnChangeTurnUC
import es.fjruiz.domain.usecase.game.UpdateGameUC
import es.fjruiz.domain.usecase.game.UpdateTimeUC
import es.fjruiz.magictimer.navigation.Destination
import es.fjruiz.magictimer.navigation.navigator.Navigator
import es.fjruiz.magictimer.ui.base.BaseViewModel
import es.fjruiz.magictimer.ui.mapper.toModel
import es.fjruiz.magictimer.ui.screens.counter.CounterUiState.Loading
import es.fjruiz.magictimer.ui.screens.counter.CounterUiState.Pause
import es.fjruiz.magictimer.ui.screens.counter.CounterUiState.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.concurrent.timer

class CounterViewModel(
    private val getLastGameUC: GetLastGameUC,
    private val isActiveGameUC: IsActiveGameUC,
    private val createGameUC: CreateGameUC,
    private val getGameUC: GetGameUC,
    private val updateGameUC: UpdateGameUC,
    private val updateTimeUC: UpdateTimeUC,
    private val getConfigUC: GetConfigUC,
    private val onChangeTurn: OnChangeTurnUC,
    private val navigator: Navigator
) : BaseViewModel<CounterIntent>() {

    private var currentId: Long = 0L
    private var timerJob: Job? = null
    private var collectJob: Job? = null
    private var lastUpdateTime = System.currentTimeMillis()
    private var adjustedSecond = 1000L

    private var _uiState: MutableStateFlow<CounterUiState> = MutableStateFlow(Loading)
    val uiState: StateFlow<CounterUiState> = _uiState

    override fun handleIntent(intent: CounterIntent) {
        when (intent) {
            CounterIntent.OnInit -> handleOnInit()
            CounterIntent.OnPauseClicked -> handleOnPausedClicked()

            is CounterIntent.OnUserClicked -> handleOnUserClicked(intent.playerId)

            CounterIntent.OnPauseDismissed -> handleOnPauseDismissed()

            is CounterIntent.OnTakePriorityClicked -> handleOnPriorityClicked(intent.playerId)
            is CounterIntent.OnTakeTurnClicked -> handleOnTurnClicked(intent.playerId)

            is CounterIntent.OnSettingsClicked -> handleOnSettingsClicked()
        }
    }

    private fun handleOnInit() {
        viewModelScope.launch(Dispatchers.IO) {
            if (!isActiveGameUC()) {
                createGameUC(getConfigUC())
            }
            collectLastGame()
        }
    }

    private fun collectLastGame() {
        startTimer()
        collectJob = viewModelScope.launch(Dispatchers.IO) {
            getLastGameUC().collect { lastGame ->
                currentId = lastGame.id
                if (lastGame.isPaused) {
                    _uiState.value = Pause
                    timerJob?.cancel()
                    timerJob = null
                    collectJob?.cancel()
                    collectJob = null
                } else {
                    lastUpdateTime = System.currentTimeMillis()
                    _uiState.value = Success(lastGame.players.map { it.toModel() })
                }
            }
        }
    }

    private fun handleOnPausedClicked() {
        timerJob?.cancel()
        timerJob = null
        viewModelScope.launch(Dispatchers.IO) {
            getGameUC(currentId)?.let { game ->
                val pausedGame = game.copy(isPaused = true)
                updateGameUC(pausedGame)
            }
        }
    }

    private fun handleOnPauseDismissed() {
        viewModelScope.launch(Dispatchers.IO) {
            getGameUC(currentId)?.let {
                val game = it.copy(isPaused = false)
                updateGameUC(game)
                collectLastGame()
            }
        }
    }

    private fun handleOnUserClicked(playerId: String) {
        // TODO: 4/11/25 Show new screen
    }

    private fun handleOnPriorityClicked(playerId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getGameUC(currentId)?.let { game ->
                val newGame =
                    game.copy(players = game.players.map { it.copy(hasPriority = it.playerId == playerId) })
                updateGameUC(newGame)
            }
        }
    }

    private fun handleOnTurnClicked(playerId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            onChangeTurn(getConfigUC(), currentId, playerId)?.let { game ->
                updateGameUC(game)
            }
        }
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = null
        timerJob = viewModelScope.launch(Dispatchers.IO) {
            while (coroutineContext.isActive) {
                getGameUC(currentId)?.let {
                    updateTimeUC(getConfigUC(), it.copy(isPaused = false))
                    delay(getTime())
                } ?: delay(1000L)
            }
        }
    }

    private fun handleOnSettingsClicked() {
        viewModelScope.launch {
            timerJob?.cancel()
            timerJob = null
            collectJob?.cancel()
            collectJob = null
            navigator.navigateTo(Destination.Settings)
        }
    }

    private fun getTime(): Long {
        if (lastUpdateTime + 1000 > System.currentTimeMillis()) {
            adjustedSecond += 10
        } else {
            adjustedSecond -= 10
        }

        return adjustedSecond
    }
}