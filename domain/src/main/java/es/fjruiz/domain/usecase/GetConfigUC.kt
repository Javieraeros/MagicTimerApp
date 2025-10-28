package es.fjruiz.domain.usecase

import es.fjruiz.domain.model.Task
import es.fjruiz.domain.repository.ConfigRepository

class GetConfigUC(private val configRepository: ConfigRepository) {
    suspend operator fun invoke(task: Task) = configRepository.getConfig()
}