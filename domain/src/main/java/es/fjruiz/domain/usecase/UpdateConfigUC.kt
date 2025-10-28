package es.fjruiz.domain.usecase

import es.fjruiz.domain.model.Config
import es.fjruiz.domain.repository.ConfigRepository

class UpdateConfigUC(private val configRepository: ConfigRepository) {
    suspend operator fun invoke(config: Config) = configRepository.updateConfig(config)
}