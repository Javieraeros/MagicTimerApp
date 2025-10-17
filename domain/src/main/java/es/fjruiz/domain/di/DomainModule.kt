package es.fjruiz.domain.di

import org.koin.dsl.module

val domainModule = module {}.plus(listOf(
    useCaseModule
))