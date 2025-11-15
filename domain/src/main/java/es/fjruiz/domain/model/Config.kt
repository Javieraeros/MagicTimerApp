package es.fjruiz.domain.model

typealias Seconds = Long

data class Config(
    val time: Seconds = 300,
    val extraTime: Seconds = 60,
    val alertTime: Seconds = 0,
    val playerNumber: PlayerNumber = PlayerNumber.FOUR
)

enum class PlayerNumber(val value: Int) {
    TWO(2), THREE(3), FOUR(4)
}
