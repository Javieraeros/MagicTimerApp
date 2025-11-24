package es.fjruiz.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.fjruiz.data.datasource.local.LocalConfigDataSource
import es.fjruiz.data.datasource.local.LocalConfigDataSourceImpl
import es.fjruiz.data.datasource.local.game.LocalGameDataSource
import es.fjruiz.data.datasource.local.game.LocalGameDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindLocalConfigDataSource(
        localDataConfigDataSourceImpl: LocalConfigDataSourceImpl
    ): LocalConfigDataSource

    @Binds
    @Singleton
    abstract fun bindLocalGameDataSource(
        localGameDataSource: LocalGameDataSourceImpl
    ): LocalGameDataSource

}