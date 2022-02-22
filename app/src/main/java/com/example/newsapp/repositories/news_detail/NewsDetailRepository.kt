package com.example.newsapp.repositories.news_detail

import com.example.newsapp.model.Articles

interface NewsDetailRepository {
    suspend fun insertArticles(articles: Articles)
    suspend fun deleteArticle(url:String)
    suspend fun getNewsUrl() : List<String>
}