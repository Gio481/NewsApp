package com.example.newsapp.di

import com.example.newsapp.repositories.news.NewsRepository
import com.example.newsapp.repositories.news.NewsRepositoryImpl
import com.example.newsapp.repositories.news_detail.NewsDetailRepository
import com.example.newsapp.repositories.news_detail.NewsDetailRepositoryImpl
import com.example.newsapp.repositories.on_boarding.OnBoardingRepository
import com.example.newsapp.repositories.on_boarding.OnBoardingRepositoryImpl
import com.example.newsapp.repositories.save_news.SaveNewsRepository
import com.example.newsapp.repositories.save_news.SaveNewsRepositoryImpl
import com.example.newsapp.repositories.search_news.SearchNewsRepository
import com.example.newsapp.repositories.search_news.SearchNewsRepositoryImpl
import com.example.newsapp.repositories.splash_screen.SplashScreenRepository
import com.example.newsapp.repositories.splash_screen.SplashScreenRepositoryImpl
import org.koin.dsl.module

object RepositoryModule {
    val repositoryModule = module {
        single<NewsRepository> { NewsRepositoryImpl(get()) }
        single<NewsDetailRepository> { NewsDetailRepositoryImpl(get()) }
        single<SaveNewsRepository> { SaveNewsRepositoryImpl(get()) }
        single<SearchNewsRepository> { SearchNewsRepositoryImpl(get()) }
        single<OnBoardingRepository> { OnBoardingRepositoryImpl(get()) }
        single<SplashScreenRepository> { SplashScreenRepositoryImpl(get()) }
    }
}