package es.fjruiz.domain.usecase

import es.fjruiz.domain.model.Task
import es.fjruiz.domain.repository.TaskRepository
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.spyk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class AddTaskUseCaseTest {

    private lateinit var repository: TaskRepository
    private lateinit var addTaskUseCase: AddTaskUseCase

    @Before
    fun setup() {
        repository = spyk()
        addTaskUseCase = AddTaskUseCase(repository)
    }

    @Test
    fun repositoryCalled_whenCallingInvoke() {
        val task = Task(1L, "test", false)

        addTaskUseCase(task)

        verify { repository.insertTask(task) }
    }

    @Test
    fun repositoryUpdateNotCalled_whenCallingInvoke() {
        val task = Task(1L, "test", false)

        addTaskUseCase(task)

        verify(inverse = true) { repository.updateTask(task) }
    }
}