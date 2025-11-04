package es.fjruiz.domain.repository

import es.fjruiz.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    suspend fun getGame(id: Long): Game?
    suspend fun getLastGame(): Flow<Game>
    suspend fun getLastUnfinishedGame(): Game?
    suspend fun isActiveGame(): Boolean
    suspend fun createGame(game: Game)
    suspend fun updateGame(game: Game)
}