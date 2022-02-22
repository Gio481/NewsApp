package com.example.newsapp.repositories.news_detail

import com.example.newsapp.db.dao.NewsDao
import com.example.newsapp.model.Articles

class NewsDetailRepositoryImpl(private val newsDao: NewsDao) : NewsDetailRepository {
    override suspend fun insertArticles(articles: Articles) = newsDao.insertArticles(articles)
    override suspend fun deleteArticle(url:String) = newsDao.deleteArticle(url)
    override suspend fun getNewsUrl(): List<String> = newsDao.getNewsUrl()
}