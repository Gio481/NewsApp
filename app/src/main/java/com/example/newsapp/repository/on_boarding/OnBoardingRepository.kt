package com.example.newsapp.repository.on_boarding

import kotlinx.coroutines.flow.Flow

interface OnBoardingRepository {
    suspend fun saveValue(key: String, value: Boolean)
}