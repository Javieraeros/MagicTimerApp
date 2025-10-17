package es.fjruiz.magictimer.ui.screens.splash

import androidx.lifecycle.viewModelScope
import es.fjruiz.magictimer.navigation.Destination
import es.fjruiz.magictimer.navigation.navigator.Navigator
import es.fjruiz.magictimer.ui.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(private val navigator: Navigator): BaseViewModel<SplashIntent>() {

    override fun handleIntent(intent: SplashIntent) {
        when (intent) {
            SplashIntent.NavigateNext -> {
                viewModelScope.launch {
                    delay(800)
                    navigator.navigatePopUp(Destination.TaskList)
                }
            }
        }
    }
}