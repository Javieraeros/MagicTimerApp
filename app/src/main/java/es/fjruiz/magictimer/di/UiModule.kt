package es.fjruiz.magictimer.di

import es.fjruiz.magictimer.navigation.navigator.Navigator
import org.koin.dsl.module

val uiModule = module {
    single { Navigator() }
}