package es.fjruiz.data.mapper

import es.fjruiz.data.dto.TaskDTO
import es.fjruiz.domain.model.Task

fun TaskDTO.toModel(): Task = Task(id, value, done)

fun List<TaskDTO>.toModel(): List<Task> = map { it.toModel() }

fun Task.toDTO(): TaskDTO = TaskDTO(id, value, done)