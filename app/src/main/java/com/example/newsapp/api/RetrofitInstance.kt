package com.example.newsapp.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://newsapi.org/"
    const val API_KEY = "e5fc2f4b1a014aada1dfaec19636a3ce"

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(NetworkInterceptor()).build()
    }

    val newsApi:NewsApi by lazy {
        retrofit.create(NewsApi::class.java)
    }
}