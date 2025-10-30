package es.fjruiz.magictimer.utils

import es.fjruiz.magictimer.ui.vo.ConfigVO

sealed class SettingException(): Exception()

class InvalidTimeException(): SettingException()
class InvalidExtraTimeException(): SettingException()
class InvalidPlayerNumberException(): SettingException()

fun isValid(configVO: ConfigVO): Boolean {
    val time = configVO.time.toIntOrNull()
    val extraTime = configVO.extraTime.toIntOrNull()
    val playerNumber = configVO.playerNumber.toIntOrNull()
    if (time == null || time < 1) {
        throw InvalidTimeException()
    }

    if (extraTime == null || extraTime < 1) {
        throw InvalidExtraTimeException()
    }

    if (playerNumber == null || playerNumber < 2 || playerNumber > 4) {
        throw InvalidPlayerNumberException()
    }

    return true
}
