package es.fjruiz.magictimer.utils

import es.fjruiz.magictimer.ui.vo.ConfigVO

sealed class SettingException(): Exception()

class InvalidTimeException(): SettingException()
class InvalidExtraTimeException(): SettingException()
class InvalidAlertTimeException(): SettingException()
class InvalidPlayerNumberException(): SettingException()

fun isValid(configVO: ConfigVO): Boolean {
    val time = configVO.time
    val extraTime = configVO.extraTime
    val alertTime = configVO.alertTime
    val playerNumber = configVO.playerNumber
    if (time < 1) {
        throw InvalidTimeException()
    }

    if (extraTime < 1) {
        throw InvalidExtraTimeException()
    }

    if (playerNumber !in 2..4) {
        throw InvalidPlayerNumberException()
    }

    if (alertTime > time) {
        throw InvalidAlertTimeException()
    }

    return true
}
