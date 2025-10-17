package es.fjruiz.magictimer.ui.screens.tasklist

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.fjruiz.commoncompose.ext.Spacer
import es.fjruiz.commoncompose.ext.rememberSaveableState
import es.fjruiz.components.button.PrimaryButton
import es.fjruiz.components.dialog.PrimaryDialog
import es.fjruiz.components.text.BodyMediumText
import es.fjruiz.components.text.TitleLargeText
import es.fjruiz.components.textfield.PrimaryTextField
import es.fjruiz.magictimer.R
import es.fjruiz.magictimer.ui.component.BaseTopBar
import es.fjruiz.magictimer.ui.vo.TaskVO
import org.koin.androidx.compose.koinViewModel

@Composable
fun TaskListScreen(taskListViewModel: TaskListViewModel = koinViewModel()) {
    val uiState by taskListViewModel.uiState.collectAsStateWithLifecycle()
    taskListViewModel.handleIntent(TaskListIntent.OnInit)
    Scaffold(topBar = {
        BaseTopBar("Tareas", Icons.Default.Home, {

        })
    }) { innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (uiState) {
                is TaskListUiState.Error -> ErrorScreen(uiState as TaskListUiState.Error)
                TaskListUiState.Loading -> LoadingScreen()
                is TaskListUiState.Success -> SuccessScreen(
                    uiState as TaskListUiState.Success,
                    taskListViewModel::handleIntent
                )
            }
        }
    }
}

@Composable
private fun BoxScope.SuccessScreen(
    uiState: TaskListUiState.Success,
    handleIntent: (TaskListIntent) -> Unit
) {
    TaskList(uiState.tasks, onCheckedChange = {
        handleIntent(TaskListIntent.OnDoneTaskClicked(it))
    }, onLongPress = {
        handleIntent(TaskListIntent.OnRemoveTaskClicked(it))
    })
    FabButton {
        handleIntent(TaskListIntent.OnAddTaskClicked)
    }

    AddTaskDialog(uiState.showDialog, onConfirmClicked = {
        handleIntent(TaskListIntent.OnAddTaskCompleted(it))
    }, onDismiss = {
        handleIntent(TaskListIntent.OnAddTaskCompleted(""))
    })
}

@Composable
private fun ErrorScreen(error: TaskListUiState.Error) {
}

@Composable
private fun BoxScope.LoadingScreen(modifier: Modifier = Modifier) {
    CircularProgressIndicator(modifier.align(Alignment.Center))
}

@Composable
fun TaskList(
    items: List<TaskVO>,
    onCheckedChange: (TaskVO) -> Unit,
    onLongPress: (TaskVO) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier.fillMaxSize()) {
        items(items, { it.id }) { task ->
            TaskItem(task, onCheckedChange = onCheckedChange, onLongPress = onLongPress)
        }
    }
}

@Composable
fun TaskItem(
    item: TaskVO,
    onCheckedChange: (TaskVO) -> Unit,
    onLongPress: (TaskVO) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .border(
                2.dp,
                MaterialTheme.colorScheme.onPrimaryContainer,
                MaterialTheme.shapes.medium
            )
            .pointerInput(Unit) {
                detectTapGestures(onLongPress = {
                    onLongPress(item)
                })
            }
    ) {
        Row(Modifier.fillMaxWidth()) {
            Checkbox(item.done, onCheckedChange = {
                onCheckedChange(item)
            })
            BodyMediumText(
                item.content, Modifier
                    .weight(1F)
                    .align(Alignment.CenterVertically)
            )

        }
    }
}

@Composable
fun BoxScope.FabButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    FloatingActionButton(
        onClick, modifier = modifier
            .align(Alignment.BottomEnd)
            .padding(16.dp)
    ) {
        Icon(Icons.Filled.Add, contentDescription = "")
    }
}

@Composable
fun AddTaskDialog(show: Boolean, onConfirmClicked: (String) -> Unit, onDismiss: () -> Unit) {
    var taskValue by rememberSaveableState("")
    if (show) {
        PrimaryDialog(onDismiss, modifier = Modifier.testTag("dialog")) {
            TitleLargeText(
                stringResource(R.string.add_task),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(16.dp)
            PrimaryTextField(taskValue, {
                taskValue = it
            })
            Spacer(8.dp)
            PrimaryButton("Añadir tarea", {
                onConfirmClicked(taskValue)
            }, Modifier.fillMaxWidth())
        }
    } else {
        taskValue = ""
    }
}