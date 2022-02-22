package com.example.newsapp.repositories.on_boarding

interface OnBoardingRepository {
    suspend fun saveValue(key: String, value: Boolean)
}