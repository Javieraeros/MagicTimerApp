package es.fjruiz.magictimer.ui.screens.counter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import es.fjruiz.magictimer.R
import es.fjruiz.magictimer.ui.base.HandleIntent
import es.fjruiz.magictimer.ui.component.LoadingView
import es.fjruiz.magictimer.ui.screens.counter.view.FourPlayerView
import es.fjruiz.magictimer.ui.screens.counter.view.ThreePlayerView
import es.fjruiz.magictimer.ui.screens.counter.view.TwoPlayerView
import es.fjruiz.magictimer.utils.AlertLauncher
import org.koin.androidx.compose.koinViewModel

private const val bottomBarBackground = "https://i.imgur.com/lP6b13v.jpg"

@Composable
fun CounterScreen(counterViewModel: CounterViewModel = koinViewModel()) {
    val state by counterViewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        counterViewModel.handleIntent(CounterIntent.OnInit)
    }
    Scaffold(bottomBar = {
        BottomBar(counterViewModel::handleIntent)
    }) { innerPadding ->
        when (state) {
            CounterUiState.Loading -> LoadingView(Modifier.padding(innerPadding))
            is CounterUiState.Pause -> PauseView(
                counterViewModel::handleIntent,
                Modifier.padding(innerPadding)
            )

            is CounterUiState.Success -> {
                val successState = (state as CounterUiState.Success)
                if (successState.alert) {
                    AlertLauncher.launch(LocalContext.current)
                }
                when (successState.counterCardModels.size) {
                    2 -> TwoPlayerView(
                        successState.counterCardModels[0],
                        successState.counterCardModels[1],
                        counterViewModel::handleIntent,
                        Modifier.padding(innerPadding)
                    )
                    3 -> ThreePlayerView(
                        successState.counterCardModels[0],
                        successState.counterCardModels[1],
                        successState.counterCardModels[2],
                        counterViewModel::handleIntent,
                        Modifier.padding(innerPadding)
                    )
                    else -> FourPlayerView(
                        successState.counterCardModels[0],
                        successState.counterCardModels[1],
                        successState.counterCardModels[2],
                        successState.counterCardModels[3],
                        counterViewModel::handleIntent,
                        Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PauseView(handleIntent: HandleIntent<CounterIntent>, modifier: Modifier = Modifier) {
    AlertDialog({
        handleIntent(CounterIntent.OnPauseDismissed)
    }, confirmButton = {
        Text(stringResource(R.string.resume), Modifier.clickable {
            handleIntent(CounterIntent.OnPauseDismissed)
        })
    }, title = {
        Text(stringResource(R.string.resume_body))
    }, modifier = modifier)
}

@Composable
fun BottomBar(handleIntent: HandleIntent<CounterIntent>,modifier: Modifier = Modifier) {
    BottomAppBar {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            AsyncImage(
                bottomBarBackground,
                contentDescription = "",
                contentScale = ContentScale.FillWidth
            )
            Icon(
                Icons.Default.Settings, "Settings", Modifier
                    .clip(CircleShape)
                    .background(Color.Black)
                    .padding(12.dp)
                    .clickable {
                        handleIntent(CounterIntent.OnSettingsClicked)
                    })
        }
    }
}