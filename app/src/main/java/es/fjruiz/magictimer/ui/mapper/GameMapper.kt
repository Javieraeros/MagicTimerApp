package es.fjruiz.magictimer.ui.mapper

import es.fjruiz.domain.model.Player
import es.fjruiz.magictimer.ui.component.card.CounterCardModel
import es.fjruiz.magictimer.ui.component.card.StarEnum
import java.text.SimpleDateFormat
import java.util.Date

fun Player.toModel(): CounterCardModel = CounterCardModel(
    playerId,
    timeLeft.toTime(),
    image,
    hasTurn,
    hasPriority,
    extraTimeLeft.toStarEnum()
)

fun Int.toStarEnum(): StarEnum = when (this) {
    0 -> StarEnum.NONE
    1 -> StarEnum.ONE
    else -> StarEnum.TWO
}

fun Long.toTime(): String {
    val dateFormat = SimpleDateFormat("mm:ss", java.util.Locale.getDefault())
    return dateFormat.format(Date(this * 1000))
}