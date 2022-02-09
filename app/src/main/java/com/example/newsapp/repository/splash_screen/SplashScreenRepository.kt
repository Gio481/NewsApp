package com.example.newsapp.repository.splash_screen

import kotlinx.coroutines.flow.Flow

interface SplashScreenRepository {
    suspend fun getValue(key: String): Flow<String?>

}