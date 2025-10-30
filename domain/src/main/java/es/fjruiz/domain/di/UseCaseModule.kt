package es.fjruiz.domain.di

import es.fjruiz.domain.usecase.AddTaskUseCase
import es.fjruiz.domain.usecase.DeleteTaskUseCase
import es.fjruiz.domain.usecase.GetConfigUC
import es.fjruiz.domain.usecase.GetTasksUseCase
import es.fjruiz.domain.usecase.UpdateConfigUC
import es.fjruiz.domain.usecase.UpdateTaskUseCase
import es.fjruiz.domain.usecase.game.CreateGameUC
import es.fjruiz.domain.usecase.game.GetLastGameUC
import es.fjruiz.domain.usecase.game.IsActiveGameUC
import es.fjruiz.domain.usecase.game.UpdateGameUC
import org.koin.dsl.module

val useCaseModule = module {
    single { GetTasksUseCase(get()) }
    single { AddTaskUseCase(get()) }
    single { UpdateTaskUseCase(get()) }
    single { DeleteTaskUseCase(get()) }

    single { GetConfigUC(get()) }
    single { UpdateConfigUC(get()) }

    single { GetLastGameUC(get()) }
    single { CreateGameUC(get()) }
    single { IsActiveGameUC(get()) }
    single { UpdateGameUC(get()) }
}