package es.fjruiz.data.repository

import es.fjruiz.data.datasource.local.game.LocalGameDataSource
import es.fjruiz.data.mapper.toDTO
import es.fjruiz.data.mapper.toModel
import es.fjruiz.domain.model.Config
import es.fjruiz.domain.model.Game
import es.fjruiz.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameRepositoryImpl(private val localGameDataSource: LocalGameDataSource): GameRepository {

    override suspend fun getGame(id: Long): Game? = localGameDataSource.getGame(id)?.toModel()
    override suspend fun getLastGame(): Flow<Game> = localGameDataSource.getLastGame().map { it.toModel() }

    override suspend fun getLastUnfinishedGame(): Game? = localGameDataSource.getLastUnfinishedGame()?.toModel()

    override suspend fun isActiveGame(): Boolean = localGameDataSource.isActiveGame()

    override suspend fun createGame(game: Game) {
        localGameDataSource.createGame(game.toDTO())
    }

    override suspend fun updateGame(game: Game) {
        localGameDataSource.updateGame(game.toDTO())
    }
}