package com.example.newsapp.di

import com.example.newsapp.network.NewsApiService
import com.example.newsapp.network.interceptor.QueryInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    private const val BASE_URL = "https://newsapi.org/"

    private fun provideUseApi(retrofit: Retrofit): NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }

    private fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(QueryInterceptor()).build()
    }

    private fun provideGsonFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideHttpClient())
            .addConverterFactory(provideGsonFactory())
            .build()
    }

    val retrofitModule = module {
        single { provideUseApi(get()) }
        single { provideHttpClient() }
        single { provideRetrofit() }
        single { provideGsonFactory() }
    }
}