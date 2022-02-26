package com.example.newsapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.newsapp.datastore.OnBoardingDataStore
import org.koin.dsl.module

object DatastoreModule {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = "onBoardingScreenDataStore"
    )

    private fun provideDataStore(context: Context): DataStore<Preferences> {
        return context.dataStore
    }

    val dataStoreModule = module {
        single { provideDataStore(get()) }
        single { OnBoardingDataStore(get()) }
    }
}