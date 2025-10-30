package es.fjruiz.data.di

import es.fjruiz.data.repository.ConfigRepositoryImpl
import es.fjruiz.data.repository.GameRepositoryImpl
import es.fjruiz.data.repository.TaskRepositoryImpl
import es.fjruiz.domain.repository.ConfigRepository
import es.fjruiz.domain.repository.GameRepository
import es.fjruiz.domain.repository.TaskRepository
import org.koin.dsl.module

internal val repositoryModule = module {
    single<TaskRepository> { TaskRepositoryImpl(get()) }
    single<ConfigRepository> { ConfigRepositoryImpl(get()) }
    single<GameRepository> { GameRepositoryImpl(get()) }
}