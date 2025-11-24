package es.fjruiz.magictimer.ui.screens.splash

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.fjruiz.magictimer.navigation.Destination
import es.fjruiz.magictimer.navigation.navigator.Navigator
import es.fjruiz.magictimer.ui.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val navigator: Navigator)
    : BaseViewModel<SplashIntent>() {

    override fun handleIntent(intent: SplashIntent) {
        when (intent) {
            SplashIntent.NavigateNext -> {
                viewModelScope.launch {
                    delay(80)
                    navigator.navigatePopUp(Destination.Counter)
                }
            }
        }
    }
}