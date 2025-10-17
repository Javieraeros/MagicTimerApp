package es.fjruiz.domain.usecase

import es.fjruiz.domain.model.Task
import es.fjruiz.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTasksUseCase(private val repository: TaskRepository) {
    operator fun invoke(): Flow<List<Task>> {
        return repository.getTasks()
    }
}