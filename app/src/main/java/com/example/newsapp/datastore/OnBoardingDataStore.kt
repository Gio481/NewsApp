package com.example.newsapp.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OnBoardingDataStore(private val dataStore: DataStore<Preferences>) {

    suspend fun saveValue(key: String, value: Boolean) {
        dataStore.edit { preference ->
            preference[stringPreferencesKey(key)] = value.toString()
        }
    }

    fun getValue(key: String): Flow<String?> {
        val preferences = dataStore.data.map { preference ->
            preference[stringPreferencesKey(key)]
        }
        return preferences
    }
}