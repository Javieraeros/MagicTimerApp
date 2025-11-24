package es.fjruiz.domain.usecase

import es.fjruiz.domain.model.Config
import es.fjruiz.domain.repository.ConfigRepository
import javax.inject.Inject

class UpdateConfigUC @Inject constructor(private val configRepository: ConfigRepository) {
    suspend operator fun invoke(config: Config) = configRepository.updateConfig(config)
}