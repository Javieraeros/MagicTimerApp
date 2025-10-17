package es.fjruiz.magictimer.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import es.fjruiz.magictimer.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(splashViewModel: SplashViewModel = koinViewModel()) {
    LaunchedEffect(Unit) {
        splashViewModel.handleIntent(SplashIntent.NavigateNext)
    }
    Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primaryContainer), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(Icons.Default.Build, contentDescription = "", modifier = Modifier.size(150.dp))
    }
}