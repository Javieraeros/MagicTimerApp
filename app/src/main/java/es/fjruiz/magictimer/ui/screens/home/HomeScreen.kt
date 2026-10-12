package es.fjruiz.magictimer.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.fjruiz.commoncompose.ext.Spacer
import es.fjruiz.commoncompose.ext.rememberSaveableState
import es.fjruiz.components.button.PrimaryButton
import es.fjruiz.components.text.BodyMediumText
import es.fjruiz.components.text.TitleLargeText
import es.fjruiz.magictimer.R
import es.fjruiz.magictimer.ui.component.LoadingView
import es.fjruiz.magictimer.ui.screens.settings.NumberRow
import es.fjruiz.magictimer.ui.vo.ConfigVO
import es.fjruiz.magictimer.ui.vo.NumberRowVO
import es.fjruiz.magictimer.ui.vo.SettingsVO

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        homeViewModel.handleIntent(HomeIntent.OnInit)
    }

    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold { innerPadding ->
        when (val state = uiState) {
            HomeUiState.Loading -> LoadingView(Modifier.padding(innerPadding))
            is HomeUiState.NewGame -> NewGameContent(
                onStartNewGame = { homeViewModel.handleIntent(HomeIntent.OnStartNewGame) },
                settingsVO = state.settings,
                modifier = Modifier.padding(innerPadding)
            )
            HomeUiState.ContinueGame -> ContinueGameContent(
                onStartNewGame = { homeViewModel.handleIntent(HomeIntent.OnStartNewGame) },
                onContinueGame = { homeViewModel.handleIntent(HomeIntent.OnContinueGame) },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
private fun NewGameContent(
    onStartNewGame: (ConfigVO) -> Unit,
    settingsVO: SettingsVO,
    modifier: Modifier = Modifier
) {
    var time by rememberSaveableState(settingsVO.time)
    var extraTime by rememberSaveableState(settingsVO.extraTime)
    var alertTime by rememberSaveableState(settingsVO.alertTime)
    var playerNumber by rememberSaveableState(settingsVO.playerNumber)
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleLargeText(text = stringResource(R.string.app_name))
        Spacer(24.dp)
        NumberRow(NumberRowVO(stringResource(R.string.time_turn), time, {
            time -= 5
        }, {
            time += 5
        }, lessButtonEnabled = time > 5))

        Spacer(8.dp)
        NumberRow(NumberRowVO(stringResource(R.string.extra_time), extraTime, {
            extraTime -= 5
        }, {
            extraTime += 5
        }, extraTime > 5))

        Spacer(8.dp)
        NumberRow(NumberRowVO(stringResource(R.string.alert_time), alertTime, {
            alertTime -= 5
        }, {
            alertTime += 5
        }, alertTime > 0, alertTime < time))

        Spacer(8.dp)
        NumberRow(NumberRowVO(stringResource(R.string.player_number), playerNumber.toLong(), {
            playerNumber--
        }, {
            playerNumber++
        }, playerNumber > 2, playerNumber < 4))

        Spacer(Modifier.weight(1F))
        Spacer(Modifier.height(32.dp))
        PrimaryButton(text = stringResource(R.string.new_game), onClick = {
            onStartNewGame(ConfigVO(time, extraTime, alertTime, playerNumber))
        })
    }
}

@Composable
private fun ContinueGameContent(
    onStartNewGame: () -> Unit,
    onContinueGame: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleLargeText(text = stringResource(R.string.app_name))
        Spacer(Modifier.height(16.dp))
        BodyMediumText(text = stringResource(R.string.game_in_progress))
        Spacer(Modifier.height(32.dp))
        PrimaryButton(text = stringResource(R.string.continue_only), onClick = onContinueGame)
        Spacer(Modifier.height(16.dp))
        PrimaryButton(text = stringResource(R.string.new_game), onClick = onStartNewGame)
    }
}
