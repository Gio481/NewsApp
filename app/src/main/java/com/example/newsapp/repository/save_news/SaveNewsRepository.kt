package com.example.newsapp.repository.save_news

import com.example.newsapp.model.Articles

interface SaveNewsRepository {
    suspend fun gelAllNews():List<Articles>
}