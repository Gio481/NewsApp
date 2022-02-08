package com.example.newsapp.repository.news_detail

import com.example.newsapp.model.Articles

interface NewsDetailRepository {
    suspend fun insertArticles(articles: Articles)
}