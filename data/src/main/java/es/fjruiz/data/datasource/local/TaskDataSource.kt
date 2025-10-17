package es.fjruiz.data.datasource.local

import es.fjruiz.data.dao.TaskDao
import es.fjruiz.data.dto.TaskDTO
import kotlinx.coroutines.flow.Flow

interface TaskDataSource {
    fun getTasks(): Flow<List<TaskDTO>>
    fun insertTask(taskDTO: TaskDTO)
    fun updateTask(taskDTO: TaskDTO)
    fun deleteTask(id: Long)
}

class TaskDataSourceImpl(private val taskDao: TaskDao): TaskDataSource {
    override fun getTasks(): Flow<List<TaskDTO>> = taskDao.getTasks()

    override fun insertTask(taskDTO: TaskDTO) = taskDao.insertTask(taskDTO)

    override fun updateTask(taskDTO: TaskDTO) = taskDao.updateTask(taskDTO)

    override fun deleteTask(id: Long) = taskDao.deleteTask(id)
}