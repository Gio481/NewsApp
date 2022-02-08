package com.example.newsapp.api

import com.example.newsapp.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("v2/top-headlines")
    suspend fun getCustomCategoryNews(
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String,
        @Query("category") category: String
    ): Response<NewsResponse>

    @GET("v2/everything")
    suspend fun getSearchedNews(
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String,
        @Query("q") q: String
    ): Response<NewsResponse>
}