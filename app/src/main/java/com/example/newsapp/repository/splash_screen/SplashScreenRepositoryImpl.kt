package com.example.newsapp.repository.splash_screen

import com.example.newsapp.datastore.OnBoardingDataStore
import kotlinx.coroutines.flow.Flow

class SplashScreenRepositoryImpl(private val dataStore: OnBoardingDataStore) : SplashScreenRepository {

    override suspend fun getValue(key: String): Flow<String?> = dataStore.getValue(key)

}