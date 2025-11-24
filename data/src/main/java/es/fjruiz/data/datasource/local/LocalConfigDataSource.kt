package es.fjruiz.data.datasource.local

import es.fjruiz.data.dto.ConfigDTO
import es.fjruiz.data.persistance.DataStoreManager
import javax.inject.Inject

interface LocalConfigDataSource {
    suspend fun getConfig(): ConfigDTO
    suspend fun updateConfig(configDTO: ConfigDTO)
}

class LocalConfigDataSourceImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : LocalConfigDataSource {

    companion object {
        private const val TIME_KEY = "time_key"
        private const val EXTRA_TIME_KEY = "extra_time_key"
        private const val ALERT_TIME_KEY = "alert_time_key"
        private const val PLAYERS_KEY = "players_key"
    }
    override suspend fun getConfig(): ConfigDTO {
        val time = dataStoreManager.getLongValue(TIME_KEY, 180)
        val extraTime = dataStoreManager.getLongValue(EXTRA_TIME_KEY, 60)
        val alertTime = dataStoreManager.getLongValue(ALERT_TIME_KEY, 0)
        val players = dataStoreManager.getIntValue(PLAYERS_KEY)

        return ConfigDTO(time, extraTime, alertTime, players)
    }

    override suspend fun updateConfig(configDTO: ConfigDTO) {
        dataStoreManager.putLongValue(TIME_KEY, configDTO.time)
        dataStoreManager.putLongValue(EXTRA_TIME_KEY, configDTO.extraTime)
        dataStoreManager.putLongValue(ALERT_TIME_KEY, configDTO.alertTime)
        dataStoreManager.putIntValue(PLAYERS_KEY, configDTO.players)
    }
}