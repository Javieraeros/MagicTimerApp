package es.fjruiz.domain.di

import es.fjruiz.domain.usecase.GetConfigUC
import es.fjruiz.domain.usecase.UpdateConfigUC
import es.fjruiz.domain.usecase.game.CreateGameUC
import es.fjruiz.domain.usecase.game.GetGameUC
import es.fjruiz.domain.usecase.game.GetLastGameUC
import es.fjruiz.domain.usecase.game.IsActiveGameUC
import es.fjruiz.domain.usecase.game.UpdateGameUC
import es.fjruiz.domain.usecase.game.UpdateTimeUC
import org.koin.dsl.module

val useCaseModule = module {
    single { GetConfigUC(get()) }
    single { UpdateConfigUC(get()) }

    single { GetGameUC(get()) }
    single { GetLastGameUC(get()) }
    single { CreateGameUC(get()) }
    single { IsActiveGameUC(get()) }
    single { UpdateGameUC(get()) }
    single { UpdateTimeUC(get()) }
}