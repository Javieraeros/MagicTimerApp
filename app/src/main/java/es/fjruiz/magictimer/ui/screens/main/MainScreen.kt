package es.fjruiz.magictimer.ui.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.fjruiz.magictimer.ui.screens.splash.SplashIntent

@Composable
fun MainScreen(mainViewModel: MainViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        mainViewModel.handleIntent(MainIntent.OnInit)
    }

    val uiState by mainViewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        MainUiState.Loading -> TODO()
        MainUiState.RenderContinueGame -> TODO()
        MainUiState.RenderNewGame -> TODO()
    }
}