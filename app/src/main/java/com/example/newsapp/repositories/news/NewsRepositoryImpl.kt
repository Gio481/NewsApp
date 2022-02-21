package com.example.newsapp.repositories.news

import com.example.newsapp.api.RetrofitInstance.newsApi
import com.example.newsapp.model.NewsResponse
import com.example.newsapp.util.Resources

class NewsRepositoryImpl : NewsRepository {
    override suspend fun getCustomCategoryNews(
        page: Int,
        category: String
    ): Resources<NewsResponse> {
        return try {
            val response =
                newsApi.getCustomCategoryNews(page = page, category = category)
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