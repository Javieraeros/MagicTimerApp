package es.fjruiz.data.di

import es.fjruiz.data.datasource.local.LocalConfigDataSource
import es.fjruiz.data.datasource.local.LocalConfigDataSourceImpl
import es.fjruiz.data.datasource.local.game.LocalGameDataSource
import es.fjruiz.data.datasource.local.game.LocalGameDataSourceImpl
import org.koin.dsl.module

internal val dataSourceModule = module {
    single<LocalConfigDataSource> { LocalConfigDataSourceImpl(get()) }
    single<LocalGameDataSource> { LocalGameDataSourceImpl(get()) }
}