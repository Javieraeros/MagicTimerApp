package es.fjruiz.data.mapper

import es.fjruiz.data.dto.ConfigDTO
import es.fjruiz.domain.model.Config
import es.fjruiz.domain.model.PlayerNumber

fun Config.toDTO(): ConfigDTO = ConfigDTO(time, extraTime, alertTime, playerNumber.toInt())

fun ConfigDTO.toModel(): Config = Config(time, extraTime, alertTime, players.toPlayerNumber())

private fun PlayerNumber.toInt(): Int = when (this) {
    PlayerNumber.TWO -> 2
    PlayerNumber.THREE -> 3
    PlayerNumber.FOUR -> 4
}

private fun Int.toPlayerNumber(): PlayerNumber = when (this) {
    2 -> PlayerNumber.TWO
    3 -> PlayerNumber.THREE
    else -> PlayerNumber.FOUR
}