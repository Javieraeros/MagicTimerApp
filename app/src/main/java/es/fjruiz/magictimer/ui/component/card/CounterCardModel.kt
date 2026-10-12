package es.fjruiz.magictimer.ui.component.card

import java.util.UUID

data class CounterCardModel(
    val playerId: String = UUID.randomUUID().toString(),
    val time: String,
    val timeLeft: Long,
    val totalTime: Long,
    val image: String,
    val hasTurn: Boolean,
    val hasPriority: Boolean,
    val stars: StarEnum,
    val totalTimeConsumed: String
) {
    val timeLeftFraction: Float
        get() = if (totalTime <= 0L) 0f else (timeLeft.toFloat() / totalTime).coerceIn(0f, 1f)
}

enum class StarEnum {
    NONE, ONE, TWO
}
