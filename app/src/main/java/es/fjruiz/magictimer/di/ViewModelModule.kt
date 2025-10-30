package es.fjruiz.magictimer.di

import es.fjruiz.magictimer.ui.screens.counter.CounterViewModel
import es.fjruiz.magictimer.ui.screens.settings.SettingsViewModel
import es.fjruiz.magictimer.ui.screens.splash.SplashViewModel
import es.fjruiz.magictimer.ui.screens.tasklist.TaskListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { SplashViewModel(get()) }
    viewModel { CounterViewModel(get()) }
    viewModel { SettingsViewModel(get(), get(), get()) }
    viewModel { TaskListViewModel(get(), get(), get(), get(), get()) }
}