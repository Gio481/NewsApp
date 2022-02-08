package com.example.newsapp.repository.news

import com.example.newsapp.model.Articles
import com.example.newsapp.model.NewsResponse
import com.example.newsapp.util.Resources

interface NewsRepository {
    suspend fun getCustomCategoryNews(
        page: Int,
        apiKey: String,
        category: String
    ): Resources<NewsResponse>
}