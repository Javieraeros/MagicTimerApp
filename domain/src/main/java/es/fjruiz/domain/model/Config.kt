package es.fjruiz.domain.model

typealias Seconds = Long

data class Config(
    val time: Seconds = 300,
    val extraTime: Seconds = 60,
    val playerNumber: PlayerNumber = PlayerNumber.FOUR
)

enum class PlayerNumber {
    TWO, THREE, FOUR
}
