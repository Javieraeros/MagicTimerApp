package es.fjruiz.magictimer.navigation.navigator

import es.fjruiz.magictimer.navigation.Destination

sealed class NavigationIntent {
    object NavigateBack: NavigationIntent()
    data class NavigateTo(val destination: Destination): NavigationIntent()
    data class NavigatePopUp(val destination: Destination): NavigationIntent()
}