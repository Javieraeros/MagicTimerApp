package es.fjruiz.magictimer.ui.vo

data class SettingsVO(
    val configVO: ConfigVO,
    val showNewGameConfirmation: Boolean,
    val showSavedCorrectly: Boolean = false
) {
    val time = configVO.time
    val extraTime = configVO.extraTime
    val alertTime = configVO.alertTime
    val playerNumber = configVO.playerNumber
}
