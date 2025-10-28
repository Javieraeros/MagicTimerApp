package es.fjruiz.data.persistance

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private const val SETTINGS_PREF_FILE = "SETTINGS_PREF"

class DataStoreManager(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(SETTINGS_PREF_FILE)

    suspend fun getLongValue(key: String): Long {
        val preferenceKey = longPreferencesKey(key)
        return context.dataStore.data.first()[preferenceKey] ?: 0L
    }

    suspend fun getIntValue(key: String): Int {
        val preferenceKey = intPreferencesKey(key)
        return context.dataStore.data.first()[preferenceKey] ?: 0
    }

    suspend fun putLongValue(key: String, value: Long) {
        val preferenceKey = longPreferencesKey(key)
        context.dataStore.edit { settings ->
            settings[preferenceKey] = value
        }
    }

    suspend fun putIntValue(key: String, value: Int) {
        val preferenceKey = intPreferencesKey(key)
        context.dataStore.edit { settings ->
            settings[preferenceKey] = value
        }
    }
}