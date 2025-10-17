package es.fjruiz.magictimer.ui.mapper

import es.fjruiz.domain.model.Task
import es.fjruiz.magictimer.ui.vo.TaskVO

fun Task.toVO(): TaskVO = TaskVO(id, value, done)

fun List<Task>.toVO(): List<TaskVO> = map { it.toVO() }

fun TaskVO.toModel(): Task = Task(id, content, done)