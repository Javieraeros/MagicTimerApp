package es.fjruiz.domain.usecase

import es.fjruiz.domain.model.Task
import es.fjruiz.domain.repository.TaskRepository

class UpdateTaskUseCase(private val taskRepository: TaskRepository) {
    operator fun invoke(task: Task) {
        taskRepository.updateTask(task)
    }
}