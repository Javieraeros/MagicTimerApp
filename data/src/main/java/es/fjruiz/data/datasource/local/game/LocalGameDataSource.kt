package es.fjruiz.data.datasource.local.game

import es.fjruiz.data.dao.GameDao
import es.fjruiz.data.dto.GameWithPlayersDTO
import kotlinx.coroutines.flow.Flow

interface LocalGameDataSource {

    suspend fun getLastGame(): Flow<GameWithPlayersDTO>
    suspend fun isActiveGame(): Boolean
    suspend fun createGame(game: GameWithPlayersDTO)
    suspend fun updateGame(game: GameWithPlayersDTO)
}

class LocalGameDataSourceImpl(private val gameDao: GameDao): LocalGameDataSource {
    override suspend fun getLastGame(): Flow<GameWithPlayersDTO> = gameDao.getLastUnfinishedGame()

    override suspend fun isActiveGame(): Boolean = gameDao.getLastGame() != null

    override suspend fun createGame(game: GameWithPlayersDTO) {
        gameDao.insertGame(game)
    }

    override suspend fun updateGame(game: GameWithPlayersDTO) {
        gameDao.updateGame(game)
    }
}