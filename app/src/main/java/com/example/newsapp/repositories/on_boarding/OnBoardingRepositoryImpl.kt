package com.example.newsapp.repositories.on_boarding

import com.example.newsapp.datastore.OnBoardingDataStore

class OnBoardingRepositoryImpl(private val dataStore: OnBoardingDataStore) : OnBoardingRepository {

    override suspend fun saveValue(key: String, value: Boolean) = dataStore.saveValue(key, value)

}