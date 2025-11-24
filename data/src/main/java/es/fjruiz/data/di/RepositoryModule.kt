package es.fjruiz.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import dagger.hilt.migration.DisableInstallInCheck
import es.fjruiz.data.repository.ConfigRepositoryImpl
import es.fjruiz.data.repository.GameRepositoryImpl
import es.fjruiz.domain.repository.ConfigRepository
import es.fjruiz.domain.repository.GameRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsConfigRepository(
        configRepositoryImpl: ConfigRepositoryImpl
    ): ConfigRepository

    @Binds
    @Singleton
    abstract fun bindsGameRepository(
        gameRepositoryImpl: GameRepositoryImpl
    ): GameRepository
}