package es.fjruiz.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import es.fjruiz.data.dto.GameDTO
import es.fjruiz.data.dto.GameWithPlayersDTO
import es.fjruiz.data.dto.PlayerDTO
import kotlinx.coroutines.flow.Flow

@Dao
abstract class GameDao {

    @Query("SELECT * from GameDTO WHERE :id == id")
    abstract fun getGame(id: Long): GameWithPlayersDTO?

    @Query("SELECT * from GameDTO WHERE isFinished == 0 ORDER BY id DESC")
    abstract fun getLastUnfinishedGame(): Flow<GameWithPlayersDTO>

    @Insert
    suspend fun insertGame(gameDTO: GameWithPlayersDTO) {
        insert(gameDTO.players)
        insert(gameDTO.game)
    }

    @Insert
    abstract suspend fun insert(playersDTO: List<PlayerDTO>)

    @Insert
    abstract suspend fun insert(gameDTO: GameDTO)

    @Transaction
    open suspend fun updateGame(gameDTO: GameWithPlayersDTO) {
        update(gameDTO.players)
        update(gameDTO.game)
    }

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun update(playersDTO: List<PlayerDTO>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun update(gameDTO: GameDTO)

    @Query("SELECT * FROM GAMEDTO WHERE isFinished == 0 ORDER BY id DESC")
    abstract suspend fun getLastGame(): GameWithPlayersDTO?

}