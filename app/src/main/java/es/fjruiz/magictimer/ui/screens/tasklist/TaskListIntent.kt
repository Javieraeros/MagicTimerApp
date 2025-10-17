package es.fjruiz.magictimer.ui.screens.tasklist

import es.fjruiz.magictimer.ui.base.BaseIntent
import es.fjruiz.magictimer.ui.vo.TaskVO

sealed class TaskListIntent: BaseIntent {
    object OnInit: TaskListIntent()

    data class OnTaskClicked(val id: Long): TaskListIntent()
    data class OnDoneTaskClicked(val task: TaskVO): TaskListIntent()
    data class OnRemoveTaskClicked(val task: TaskVO): TaskListIntent()
    data object OnAddTaskClicked: TaskListIntent()
    data class OnAddTaskCompleted(val value: String): TaskListIntent()
}