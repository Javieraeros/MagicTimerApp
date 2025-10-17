package es.fjruiz.magictimer.ui.screens.tasklist

import es.fjruiz.magictimer.ui.vo.TaskVO

sealed class TaskListUiState {
    data class Success(
        val tasks: List<TaskVO> = emptyList(),
        val showDialog: Boolean = false
    ): TaskListUiState()

    data class Error(
        val error: Throwable
    ): TaskListUiState()
    data object Loading: TaskListUiState()
}
