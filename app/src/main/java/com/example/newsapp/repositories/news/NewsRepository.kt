package com.example.newsapp.repositories.news

import com.example.newsapp.model.NewsResponse
import com.example.newsapp.util.Resources

interface NewsRepository {
    suspend fun getCustomCategoryNews(
        page: Int,
        category: String
    ): Resources<NewsResponse>
}