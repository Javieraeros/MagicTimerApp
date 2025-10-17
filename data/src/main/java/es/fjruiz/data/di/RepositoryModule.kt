package es.fjruiz.data.di

import es.fjruiz.data.repository.TaskRepositoryImpl
import es.fjruiz.domain.repository.TaskRepository
import org.koin.dsl.module

internal val repositoryModule = module {
    single<TaskRepository> { TaskRepositoryImpl(get()) }
}