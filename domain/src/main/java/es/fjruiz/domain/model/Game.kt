package es.fjruiz.domain.model

data class Game(
    val id: Long,
    val players: List<Player>,
    val isPaused: Boolean,
    val isFinished: Boolean
)
