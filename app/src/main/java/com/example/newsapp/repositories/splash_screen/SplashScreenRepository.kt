package com.example.newsapp.repositories.splash_screen

import kotlinx.coroutines.flow.Flow

interface SplashScreenRepository {
    suspend fun getValue(key: String): Flow<String?>

}