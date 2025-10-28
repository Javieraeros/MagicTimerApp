package es.fjruiz.data.datasource.local

import es.fjruiz.data.dto.ConfigDTO
import es.fjruiz.data.persistance.DataStoreManager

interface LocalConfigDataSource {
    suspend fun getConfig(): ConfigDTO
    suspend fun updateConfig(configDTO: ConfigDTO)
}

class LocalConfigDataSourceImpl(
    private val dataStoreManager: DataStoreManager
) : LocalConfigDataSource {

    companion object {
        private const val TIME_KEY = "time_key"
        private const val EXTRA_TIME_KEY = "extra_time_key"
        private const val PLAYERS_KEY = "players_key"
    }
    override suspend fun getConfig(): ConfigDTO {
        val time = dataStoreManager.getLongValue(TIME_KEY)
        val extraTime = dataStoreManager.getLongValue(EXTRA_TIME_KEY)
        val players = dataStoreManager.getIntValue(PLAYERS_KEY)

        return ConfigDTO(time, extraTime, players)
    }

    override suspend fun updateConfig(configDTO: ConfigDTO) {
        dataStoreManager.putLongValue(TIME_KEY, configDTO.time)
        dataStoreManager.putLongValue(EXTRA_TIME_KEY, configDTO.extraTime)
        dataStoreManager.putIntValue(PLAYERS_KEY, configDTO.players)
    }
}