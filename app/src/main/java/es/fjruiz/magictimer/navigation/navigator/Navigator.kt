package es.fjruiz.magictimer.navigation.navigator

import es.fjruiz.magictimer.navigation.Destination
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel

class Navigator {
    val navigationChannel =  Channel<NavigationIntent>(
        capacity = Int.MAX_VALUE,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )

    fun navigateBack() {
        navigationChannel.trySend(NavigationIntent.NavigateBack)
    }

    fun navigateTo(destination: Destination) {
        navigationChannel.trySend(NavigationIntent.NavigateTo(destination))
    }

    fun navigatePopUp(destination: Destination) {
        navigationChannel.trySend(NavigationIntent.NavigatePopUp(destination))
    }
}