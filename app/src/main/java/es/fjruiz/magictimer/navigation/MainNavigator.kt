package es.fjruiz.magictimer.navigation

import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import es.fjruiz.magictimer.navigation.graph.MainGraph
import es.fjruiz.magictimer.navigation.navigator.NavigationIntent
import es.fjruiz.magictimer.navigation.navigator.Navigator
import es.fjruiz.magictimer.ui.theme.MagicTimerTheme
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.compose.koinInject

@Composable
fun MainNavigation(navigator: Navigator = koinInject()) {
    val navController = rememberNavController()
    val activity = LocalActivity.current
    val navChannel = navigator.navigationChannel


    LaunchedEffect(activity, navController, navChannel) {
        navChannel.receiveAsFlow().collect { intent ->
            if (activity?.isFinishing != true) {
                when (intent) {
                    NavigationIntent.NavigateBack -> navController.popBackStack()
                    is NavigationIntent.NavigateTo -> navController.navigate(intent.destination)
                    is NavigationIntent.NavigatePopUp -> navController.navigate(intent.destination) {
                        launchSingleTop = true
                        popUpTo(navController.graph.findStartDestination().id) { inclusive = true }
                    }
                }
            }
        }
    }

    MagicTimerTheme {
        MainGraph(navController)
    }
}