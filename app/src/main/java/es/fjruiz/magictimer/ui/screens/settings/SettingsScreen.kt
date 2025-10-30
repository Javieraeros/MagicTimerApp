@file:OptIn(ExperimentalMaterial3Api::class)

package es.fjruiz.magictimer.ui.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.fjruiz.commoncompose.ext.Spacer
import es.fjruiz.components.button.PrimaryButton
import es.fjruiz.components.text.BodyMediumText
import es.fjruiz.components.text.TitleLargeText
import es.fjruiz.components.text.TitleMediumText
import es.fjruiz.components.textfield.DigitTextField
import es.fjruiz.magictimer.R
import es.fjruiz.magictimer.ui.base.HandleIntent
import es.fjruiz.magictimer.ui.component.LoadingView
import es.fjruiz.magictimer.ui.vo.ConfigVO
import es.fjruiz.magictimer.ui.vo.SettingsVO
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(settingsViewModel: SettingsViewModel = koinViewModel()) {
    LaunchedEffect(Unit) {
        settingsViewModel.handleIntent(SettingsIntent.OnInit)
    }

    val uiState by settingsViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(topBar = {
        SettingsTopAppBar(settingsViewModel::handleIntent)
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
    val time = TextFieldState(settingsVO.time)
    val extraTime = TextFieldState(settingsVO.extraTime)
    val playerNumber = TextFieldState(settingsVO.playerNumber)

    Column(modifier) {
        Spacer(8.dp)
        TimeRow(time)
        Spacer(8.dp)
        ExtraTimeRow(extraTime)
        Spacer(8.dp)
        PlayerNumberRow(playerNumber)
        Spacer(Modifier.weight(1F))
        Row(Modifier.padding(horizontal = 12.dp, vertical = 12.dp)) {
            PrimaryButton(stringResource(R.string.new_game), {
                handleIntent(SettingsIntent.OnNewGameClicked)
            })
            Spacer(Modifier.weight(1f))
            PrimaryButton(stringResource(R.string.save), {
                handleIntent(SettingsIntent.Save(ConfigVO(time.text.toString(), extraTime.text.toString(), playerNumber.text.toString())))
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
private fun TimeRow(
    time: TextFieldState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .padding(12.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        TitleMediumText(stringResource(R.string.time_turn))
        Spacer(Modifier.weight(1F))
        DigitTextField(time, Modifier.width(80.dp))
    }
}

@Composable
private fun ExtraTimeRow(
    extraTime: TextFieldState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .padding(12.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        TitleMediumText(stringResource(R.string.extra_time))
        Spacer(Modifier.weight(1F))
        DigitTextField(extraTime, Modifier.width(80.dp))
    }
}

@Composable
private fun PlayerNumberRow(
    playerNumber: TextFieldState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .padding(12.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        TitleMediumText(stringResource(R.string.player_number))
        Spacer(Modifier.weight(1F))
        DigitTextField(playerNumber, Modifier.width(80.dp))
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