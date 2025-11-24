package es.fjruiz.data.repository

import es.fjruiz.data.datasource.local.LocalConfigDataSource
import es.fjruiz.data.mapper.toDTO
import es.fjruiz.data.mapper.toModel
import es.fjruiz.domain.model.Config
import es.fjruiz.domain.repository.ConfigRepository
import javax.inject.Inject

class ConfigRepositoryImpl
    @Inject constructor(private val localDataSource: LocalConfigDataSource): ConfigRepository {
    override suspend fun getConfig(): Config {
        return localDataSource.getConfig().toModel()
    }

    override suspend fun updateConfig(config: Config) {
        localDataSource.updateConfig(config.toDTO())
    }
}