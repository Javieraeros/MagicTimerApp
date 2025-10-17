package es.fjruiz.magictimer.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import es.fjruiz.magictimer.navigation.Destination
import es.fjruiz.magictimer.ui.screens.tasklist.TaskListScreen
import es.fjruiz.magictimer.ui.screens.splash.SplashScreen

@Composable
fun MainGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Destination.Splash) {
        composable<Destination.Splash> {
            SplashScreen()
        }

        composable<Destination.TaskList> {
            TaskListScreen()
        }
    }
}