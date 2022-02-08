package com.example.newsapp.repository.search_news

import com.example.newsapp.api.RetrofitInstance.newsApi
import com.example.newsapp.model.NewsResponse
import com.example.newsapp.util.Resources

class SearchNewsRepositoryImpl : SearchNewsRepository {
    override suspend fun getSearchedNews(
        page: Int,
        apiKey: String,
        q: String
    ): Resources<NewsResponse> {
        return try {
            val response = newsApi.getSearchedNews(page = page, apiKey = apiKey, q = q)
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