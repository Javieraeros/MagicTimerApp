package es.fjruiz.domain.usecase

import es.fjruiz.domain.repository.ConfigRepository
import javax.inject.Inject

class GetConfigUC @Inject constructor(private val configRepository: ConfigRepository) {
    suspend operator fun invoke() = configRepository.getConfig()
}