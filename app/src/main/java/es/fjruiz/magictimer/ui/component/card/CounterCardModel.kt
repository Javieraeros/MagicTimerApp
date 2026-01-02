package es.fjruiz.magictimer.ui.component.card

import java.util.UUID

data class CounterCardModel(
    val playerId: String = UUID.randomUUID().toString(),
    val time: String,
    val image: String,
    val hasTurn: Boolean,
    val hasPriority: Boolean,
    val stars: StarEnum,
    val totalTimeConsumed: String
)

enum class StarEnum {
    NONE, ONE, TWO
}
