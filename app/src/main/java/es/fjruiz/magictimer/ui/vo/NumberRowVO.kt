package es.fjruiz.magictimer.ui.vo

data class NumberRowVO(
    val label: String,
    val value: Long,
    val onLessClicked: () -> Unit,
    val onMoreClicked: () -> Unit,
    val lessButtonEnabled: Boolean = true,
    val moreButtonEnabled: Boolean= true,
)
