package com.example.newsapp.api

import com.example.newsapp.api.interceptor.QueryInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://newsapi.org/"

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(QueryInterceptor()).build()
    }

    val newsApi:NewsApi by lazy {
        retrofit.create(NewsApi::class.java)
    }
}