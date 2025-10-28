package es.fjruiz.data.di

import org.koin.dsl.module

val dataModule = module {}
    .plus(databaseModule)
    .plus(dataSourceModule)
    .plus(repositoryModule)
    .plus(persistenceModule)