package es.fjruiz.magictimer.di

import org.koin.dsl.module

val appModule = module {  }.plus(uiModule).plus(viewModelModule)