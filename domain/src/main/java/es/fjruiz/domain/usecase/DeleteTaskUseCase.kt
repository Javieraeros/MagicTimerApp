package es.fjruiz.domain.usecase

import es.fjruiz.domain.repository.TaskRepository

class DeleteTaskUseCase(private val taskRepository: TaskRepository) {

    operator fun invoke(id: Long) {
        taskRepository.deleteTask(id)
    }
}