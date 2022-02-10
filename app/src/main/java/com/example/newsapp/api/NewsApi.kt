package com.example.newsapp.api

import com.example.newsapp.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("v2/top-headlines")
    suspend fun getCustomCategoryNews(
        @Query("category") category: String,
        @Query("page") page: Int,
    ): Response<NewsResponse>

    @GET("v2/everything")
    suspend fun getSearchedNews(
        @Query("q") q: String,
        @Query("page") page: Int,
    ): Response<NewsResponse>
}