package com.example.newsapp.repository.search_news

import com.example.newsapp.model.NewsResponse
import com.example.newsapp.util.Resources


interface SearchNewsRepository {
    suspend fun getSearchedNews(page: Int, apiKey: String, q: String): Resources<NewsResponse>
}