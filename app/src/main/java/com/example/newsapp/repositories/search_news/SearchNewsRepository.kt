package com.example.newsapp.repositories.search_news

import com.example.newsapp.model.NewsResponse
import com.example.newsapp.util.Resources


interface SearchNewsRepository {
    suspend fun getSearchedNews(page: Int, q: String): Resources<NewsResponse>
}