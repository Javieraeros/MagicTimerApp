package es.fjruiz.magictimer.ui.mapper

import es.fjruiz.domain.model.Config
import es.fjruiz.domain.model.PlayerNumber
import es.fjruiz.magictimer.ui.vo.ConfigVO

fun Config.toVO(): ConfigVO = ConfigVO(time, extraTime, alertTime, playerNumber.toVO())

private fun PlayerNumber.toVO(): Int = when (this) {
    PlayerNumber.TWO -> 2
    PlayerNumber.THREE -> 3
    PlayerNumber.FOUR -> 4
}

fun ConfigVO.toModel(): Config = Config(time, extraTime, alertTime, playerNumber.toPlayerNumber())

fun Int.toPlayerNumber(): PlayerNumber = when (this) {
    2 -> PlayerNumber.TWO
    3 -> PlayerNumber.THREE
    else -> PlayerNumber.FOUR
}