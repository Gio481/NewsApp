package com.example.newsapp.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OnBoardingDataStore(context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = PREFERENCE_DATA_STORE_NAME
    )
    private val dataStore: DataStore<Preferences> = context.dataStore

    suspend fun saveValue(key: String, value: String) {
        dataStore.edit { preference ->
            preference[stringPreferencesKey(key)] = value
        }
    }

    fun getValue(key: String): Flow<String> {
        val preferences = dataStore.data.map { preference ->
            preference[stringPreferencesKey(key)] ?: VALUE_NOT_FOUND
        }
        return preferences
    }


    companion object {
        private const val VALUE_NOT_FOUND = "Value not found"
        private const val PREFERENCE_DATA_STORE_NAME = "onBoardingScreenDataStore"
    }
}