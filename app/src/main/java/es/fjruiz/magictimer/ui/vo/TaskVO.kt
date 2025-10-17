package es.fjruiz.magictimer.ui.vo

data class TaskVO(
    val id: Long,
    val content: String,
    val done: Boolean = false
)
