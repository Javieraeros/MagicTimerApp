package es.fjruiz.magictimer.ui.vo

data class SettingsVO(
    val configVO: ConfigVO,
    val showNewGameConfirmation: Boolean
) {
    val time = configVO.time
    val extraTime = configVO.extraTime
    val playerNumber = configVO.playerNumber
}
