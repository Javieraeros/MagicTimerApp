@file:OptIn(ExperimentalMaterial3Api::class)

package es.fjruiz.magictimer.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import es.fjruiz.commoncompose.ext.Spacer
import es.fjruiz.commoncompose.ext.rememberSaveableState
import es.fjruiz.components.button.PrimaryButton
import es.fjruiz.components.text.BodyMediumText
import es.fjruiz.components.text.TitleLargeText
import es.fjruiz.components.text.TitleMediumText
import es.fjruiz.components.text.TitleSmallText
import es.fjruiz.magictimer.R
import es.fjruiz.magictimer.ui.base.HandleIntent
import es.fjruiz.magictimer.ui.component.LoadingView
import es.fjruiz.magictimer.ui.vo.ConfigVO
import es.fjruiz.magictimer.ui.vo.NumberRowVO
import es.fjruiz.magictimer.ui.vo.SettingsVO
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(settingsViewModel: SettingsViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        settingsViewModel.handleIntent(SettingsIntent.OnInit)
    }

    val uiState by settingsViewModel.uiState.collectAsStateWithLifecycle()
    val rememberCoroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    (uiState as? SettingsUiState.Success)?.takeIf { it.settingsVO.showSavedCorrectly }?.let {
        val savedMessage = stringResource(R.string.success_saved)
        rememberCoroutineScope.launch {
            snackBarHostState.showSnackbar(savedMessage)
        }
    }

    Scaffold(topBar = {
        SettingsTopAppBar(settingsViewModel::handleIntent)
    }, snackbarHost = {
        SnackbarHost(snackBarHostState)
    }) { paddingValues ->
        when (uiState) {
            SettingsUiState.Loading -> LoadingView(Modifier.padding(paddingValues))

            is SettingsUiState.Error -> ErrorView(
                (uiState as SettingsUiState.Error).errorType,
                settingsViewModel::handleIntent,
                Modifier.padding(paddingValues)
            )

            is SettingsUiState.Success -> SettingsContent(
                (uiState as SettingsUiState.Success).settingsVO,
                settingsViewModel::handleIntent,
                Modifier.padding(paddingValues)
            )
        }
    }
}

@Composable
private fun SettingsContent(
    settingsVO: SettingsVO,
    handleIntent: HandleIntent<SettingsIntent>,
    modifier: Modifier = Modifier
) {
    var time by rememberSaveableState(settingsVO.time)
    var extraTime by rememberSaveableState(settingsVO.extraTime)
    var alertTime by rememberSaveableState(settingsVO.alertTime)
    var playerNumber by rememberSaveableState(settingsVO.playerNumber)

    Column(modifier) {
        Spacer(8.dp)
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
        Row(Modifier.padding(horizontal = 24.dp, vertical = 60.dp)) {
            PrimaryButton(stringResource(R.string.new_game), {
                handleIntent(SettingsIntent.OnNewGameClicked)
            })
            Spacer(Modifier.weight(1f))
            PrimaryButton(stringResource(R.string.save), {
                handleIntent(SettingsIntent.Save(ConfigVO(time, extraTime, alertTime, playerNumber)))
            })
        }
    }

    if (settingsVO.showNewGameConfirmation) {
        NewGameConfirmationView(handleIntent)
    }
}

@Composable
private fun SettingsTopAppBar(
    handleIntent: HandleIntent<SettingsIntent>,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { TitleLargeText(stringResource(R.string.settings)) },
        modifier = modifier,
        navigationIcon = {
            Icon(
                Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier.clickable {
                    handleIntent(SettingsIntent.OnCloseClicked)
                })
        }
    )
}

@Composable
fun NumberRow(
    numberRowVO: NumberRowVO,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TitleMediumText(numberRowVO.label)
        Spacer(Modifier.weight(1F))
        Icon(
            Icons.Default.Remove,
            "Less",
            Modifier
                .clickable(numberRowVO.lessButtonEnabled, onClick = numberRowVO.onLessClicked)
                .background(
                    MaterialTheme.colorScheme.primaryContainer, shape = CircleShape
                ),
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
        TitleSmallText(
            numberRowVO.value.toString(), Modifier
                .padding(horizontal = 8.dp)
                .width(60.dp)
                .border(1.dp, Color.White, shape = MaterialTheme.shapes.small),
            textAlign = TextAlign.Center
        )
        Icon(
            Icons.Default.Add,
            "More",
            Modifier
                .clickable(numberRowVO.moreButtonEnabled, onClick = numberRowVO.onMoreClicked)
                .background(
                    MaterialTheme.colorScheme.primaryContainer, shape = CircleShape
                ),
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
private fun ErrorView(
    settingsError: SettingsError,
    handleIntent: HandleIntent<SettingsIntent>,
    modifier: Modifier = Modifier
) {
    val errorBody = when (settingsError) {
        SettingsError.InvalidExtraTime -> R.string.invalid_extra_time
        SettingsError.InvalidPlayerNumber -> R.string.invalid_player_number
        SettingsError.InvalidAlertTime -> R.string.invalid_alert_time
        SettingsError.InvalidTime -> R.string.invalid_time
    }
    AlertDialog({
        handleIntent(SettingsIntent.OnCloseErrorClicked)
    }, confirmButton = {
        PrimaryButton(stringResource(R.string.close), {
            handleIntent(SettingsIntent.OnCloseErrorClicked)
        })
    }, title = {
        TitleLargeText(stringResource(R.string.error))
    }, text = {
        BodyMediumText(stringResource(errorBody))
    })
}

@Composable
private fun NewGameConfirmationView(
    handleIntent: HandleIntent<SettingsIntent>,
    modifier: Modifier = Modifier
) {
    AlertDialog({
        handleIntent(SettingsIntent.OnCancelNewGame)
    }, confirmButton = {
        PrimaryButton(stringResource(R.string.ok), {
            handleIntent(SettingsIntent.OnConfirmNewGame)
        })
    }, dismissButton = {
        PrimaryButton(stringResource(R.string.cancel), {
            handleIntent(SettingsIntent.OnCancelNewGame)
        })
    }, title = {
        TitleLargeText(stringResource(R.string.new_game))
    }, text = {
        BodyMediumText(stringResource(R.string.new_game_body))
    })
}