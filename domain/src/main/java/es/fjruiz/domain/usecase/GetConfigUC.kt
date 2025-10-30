package es.fjruiz.domain.usecase

import es.fjruiz.domain.repository.ConfigRepository

class GetConfigUC(private val configRepository: ConfigRepository) {
    suspend operator fun invoke() = configRepository.getConfig()
}