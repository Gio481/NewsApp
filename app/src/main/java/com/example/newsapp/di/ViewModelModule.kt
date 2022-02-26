package com.example.newsapp.di

import com.example.newsapp.presenter.news.NewsViewModel
import com.example.newsapp.presenter.news_detail.NewsDetailViewModel
import com.example.newsapp.presenter.on_boarding.OnBoardingViewModel
import com.example.newsapp.presenter.saved_news.SavedNewsViewModel
import com.example.newsapp.presenter.search_news.SearchNewsViewModel
import com.example.newsapp.presenter.splash_screen.SplashScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {
    val viewModelModule = module {
        viewModel { NewsViewModel(get()) }
        viewModel { NewsDetailViewModel(get()) }
        viewModel { SavedNewsViewModel(get()) }
        viewModel { SearchNewsViewModel(get()) }
        viewModel { SplashScreenViewModel(get()) }
        viewModel { OnBoardingViewModel(get()) }
    }
}