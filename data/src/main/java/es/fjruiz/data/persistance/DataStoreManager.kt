package es.fjruiz.data.persistance

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.serialization.EncodeDefault

private const val SETTINGS_PREF_FILE = "SETTINGS_PREF"

class DataStoreManager(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(SETTINGS_PREF_FILE)

    suspend fun getLongValue(key: String, default: Long = 0): Long {
        val preferenceKey = longPreferencesKey(key)
        return context.dataStore.data.first()[preferenceKey] ?: default
    }

    suspend fun getIntValue(key: String, default: Int = 0): Int {
        val preferenceKey = intPreferencesKey(key)
        return context.dataStore.data.first()[preferenceKey] ?: default
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