package com.example.newsapp.repository.news_detail

import com.example.newsapp.model.Articles

interface NewsDetailRepository {
    suspend fun insertArticles(articles: Articles)
    suspend fun deleteArticle(articles: Articles)
    suspend fun updateArticle(articles: Articles)
    suspend fun getNewsUrl() : List<String>
}