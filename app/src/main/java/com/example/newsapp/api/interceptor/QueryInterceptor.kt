package com.example.newsapp.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class QueryInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url.newBuilder().addQueryParameter(QUERY, API_KEY).build()
        val request = chain.request().newBuilder().url(url).build()
        return chain.proceed(request)
    }

    companion object {
        private const val QUERY = "apiKey"
        private const val API_KEY = "e5fc2f4b1a014aada1dfaec19636a3ce"

    }
}