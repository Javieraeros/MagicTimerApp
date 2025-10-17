package es.fjruiz.data.repository

import es.fjruiz.data.datasource.local.TaskDataSource
import es.fjruiz.data.mapper.toDTO
import es.fjruiz.data.mapper.toModel
import es.fjruiz.domain.model.Task
import es.fjruiz.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(private val taskDataSource: TaskDataSource): TaskRepository {
    override fun getTasks(): Flow<List<Task>> = taskDataSource.getTasks().map { it.toModel() }

    override fun insertTask(task: Task) {
        taskDataSource.insertTask(task.toDTO())
    }

    override fun updateTask(task: Task) {
        taskDataSource.updateTask(task.toDTO())
    }

    override fun deleteTask(id: Long) {
        taskDataSource.deleteTask(id)
    }
}