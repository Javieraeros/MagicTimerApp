package es.fjruiz.domain.repository

import es.fjruiz.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getTasks(): Flow<List<Task>>

    fun insertTask(task: Task)

    fun updateTask(task: Task)

    fun deleteTask(id: Long)
}