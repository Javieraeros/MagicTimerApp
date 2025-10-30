package es.fjruiz.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import es.fjruiz.domain.model.Seconds

@Entity
data class PlayerDTO(
    @PrimaryKey val playerId: String,
    val name: String,
    val image: String,
    val timeLeft: Seconds,
    val hasTurn: Boolean,
    val hasPriority: Boolean,
    val extraTimeLeft: Int,
    val isExtraTimeRunning: Boolean,
    val gameId: Long
)