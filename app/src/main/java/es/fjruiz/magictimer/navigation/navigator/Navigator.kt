package es.fjruiz.magictimer.navigation.navigator

import es.fjruiz.magictimer.navigation.Destination
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject

interface Navigator {
    val navigationChannel: Channel<NavigationIntent>
    fun navigateBack()
    fun navigateTo(destination: Destination)
    fun navigatePopUp(destination: Destination)
}

class NavigatorImpl @Inject constructor(): Navigator {
    init {
        println("Javi delete: init called ${hashCode()}")
    }
    override val navigationChannel =  Channel<NavigationIntent>(
        capacity = Int.MAX_VALUE,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )

    override fun navigateBack() {
        navigationChannel.trySend(NavigationIntent.NavigateBack)
    }

    override fun navigateTo(destination: Destination) {
        navigationChannel.trySend(NavigationIntent.NavigateTo(destination))
    }

    override fun navigatePopUp(destination: Destination) {
        println("Javi delete: navigatePopUp $destination")
        navigationChannel.trySend(NavigationIntent.NavigatePopUp(destination))
    }
}