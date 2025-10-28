package es.fjruiz.data.di

import es.fjruiz.data.persistance.DataStoreManager
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

internal val persistenceModule = module {
    single { DataStoreManager(androidApplication()) }
}