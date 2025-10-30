package es.fjruiz.data.dto

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class GameDTO(
    @PrimaryKey val id: Long,
    val isPaused: Boolean,
    val isFinished: Boolean
)

data class GameWithPlayersDTO(
    @Embedded val game: GameDTO,
    @Relation(
        parentColumn = "id",
        entityColumn = "gameId"
    ) val players: List<PlayerDTO>
) {
    val id by lazy { game.id }
    val isPaused by lazy { game.isPaused }
    val isFinished by lazy { game.isFinished }
}
