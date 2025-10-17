package es.fjruiz.magictimer.ui.screens.tasklist

import androidx.lifecycle.viewModelScope
import es.fjruiz.domain.model.Task
import es.fjruiz.domain.usecase.AddTaskUseCase
import es.fjruiz.domain.usecase.DeleteTaskUseCase
import es.fjruiz.domain.usecase.GetTasksUseCase
import es.fjruiz.domain.usecase.UpdateTaskUseCase
import es.fjruiz.magictimer.navigation.navigator.Navigator
import es.fjruiz.magictimer.ui.base.BaseViewModel
import es.fjruiz.magictimer.ui.mapper.toModel
import es.fjruiz.magictimer.ui.mapper.toVO
import es.fjruiz.magictimer.ui.screens.tasklist.TaskListUiState.Success
import es.fjruiz.magictimer.ui.vo.TaskVO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskListViewModel(
    private val getTasksUseCase: GetTasksUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val navigator: Navigator
): BaseViewModel<TaskListIntent>() {

    private var _uiState: MutableStateFlow<TaskListUiState> = MutableStateFlow(TaskListUiState.Loading)
    val uiState: StateFlow<TaskListUiState> = _uiState

    override fun handleIntent(intent: TaskListIntent) {
        when (intent) {
            TaskListIntent.OnInit -> handleOnInit()
            TaskListIntent.OnAddTaskClicked -> handleOnAddTaskClicked()
            is TaskListIntent.OnTaskClicked -> TODO()
            is TaskListIntent.OnAddTaskCompleted -> handleOnAddTaskCompleted(intent.value)
            is TaskListIntent.OnDoneTaskClicked -> handleOnDoneTaskClicked(intent.task)
            is TaskListIntent.OnRemoveTaskClicked -> handleOnRemoveTaskClicked(intent.task)
        }
    }

    private fun handleOnInit() {
        viewModelScope.launch {
            getTasksUseCase().map { it.toVO() }.collect {
                _uiState.value = TaskListUiState.Success(it, false)
            }
        }
    }

    private fun handleOnAddTaskClicked() {
        _uiState.update {
            when (it) {
                is TaskListUiState.Error -> it
                TaskListUiState.Loading -> it
                is Success -> it.copy(showDialog = true)
            }
        }
    }

    private fun handleOnAddTaskCompleted(value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            addTaskUseCase(Task(System.currentTimeMillis(), value, false))
        }
    }

    private fun handleOnDoneTaskClicked(task: TaskVO) {
        viewModelScope.launch(Dispatchers.IO) {
            updateTaskUseCase(task.copy(done = task.done.not()).toModel())
        }
    }

    private fun handleOnRemoveTaskClicked(task: TaskVO) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteTaskUseCase(task.id)
        }
    }
}