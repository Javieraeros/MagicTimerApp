package es.fjruiz.domain.model

import java.util.UUID

data class Player(
    val playerId: String = UUID.randomUUID().toString(),
    val name: String,
    val image: String,
    val timeLeft: Seconds,
    val hasTurn: Boolean,
    val hasPriority: Boolean,
    val extraTimeLeft: Int,
    val isExtraTimeRunning: Boolean
)
