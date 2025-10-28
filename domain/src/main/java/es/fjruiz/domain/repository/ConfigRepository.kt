package es.fjruiz.domain.repository

import es.fjruiz.domain.model.Config
import kotlinx.coroutines.flow.Flow

interface ConfigRepository {
    suspend fun getConfig(): Config
    suspend fun updateConfig(config: Config)
}