package com.example.newsapp.repositories.search_news

import com.example.newsapp.network.NewsApiService
import com.example.newsapp.model.NewsResponse
import com.example.newsapp.util.Resources

class SearchNewsRepositoryImpl(private val newsApiService: NewsApiService) : SearchNewsRepository {
    override suspend fun getSearchedNews(
        page: Int,
        q: String
    ): Resources<NewsResponse> {
        return try {
            val response = newsApiService.getSearchedNews(page = page, q = q)
            if (response.isSuccessful) {
                Resources.Success(response.body())
            } else {
                Resources.Error(response.message())
            }
        } catch (e: Exception) {
            Resources.Error(e.message)
        }
    }

}