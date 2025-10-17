package es.fjruiz.magictimer.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Destination {

    @Serializable
    object Splash: Destination()


    @Serializable
    object TaskList: Destination()
}