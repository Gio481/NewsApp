package com.example.newsapp.repositories.save_news

import com.example.newsapp.db.dao.NewsDao
import com.example.newsapp.model.Articles

class SaveNewsRepositoryImpl(private val newsDao: NewsDao) : SaveNewsRepository {
    override suspend fun gelAllNews(): List<Articles> = newsDao.getAllNews()
}