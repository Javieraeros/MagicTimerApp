package es.fjruiz.domain.repository

import es.fjruiz.domain.model.Config
import es.fjruiz.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    suspend fun getLastGame(): Flow<Game>
    suspend fun isActiveGame(): Boolean
    suspend fun createGame(game: Game)
    suspend fun updateGame(game: Game)
}