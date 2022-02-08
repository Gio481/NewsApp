package com.example.newsapp.repository.news_detail

import com.example.newsapp.db.dao.NewsDao
import com.example.newsapp.model.Articles

class NewsDetailRepositoryImpl(private val newsDao: NewsDao) : NewsDetailRepository {
    override suspend fun insertArticles(articles: Articles) = newsDao.insertArticles(articles)
}