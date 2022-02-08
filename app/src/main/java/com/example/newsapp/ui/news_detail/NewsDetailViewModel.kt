package com.example.newsapp.ui.news_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.App
import com.example.newsapp.model.Articles
import com.example.newsapp.model.NewsResponse
import com.example.newsapp.repository.news_detail.NewsDetailRepository
import com.example.newsapp.repository.news_detail.NewsDetailRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsDetailViewModel : ViewModel() {
    private val repository: NewsDetailRepository by lazy { NewsDetailRepositoryImpl(App.db.newsDao()) }

    private val _successNewsLiveData: MutableLiveData<Articles> = MutableLiveData()
    val successNewsLiveData: LiveData<Articles> get() = _successNewsLiveData

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: MutableLiveData<String> get() = _errorLiveData

    fun insertArticle(articles: Articles) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertArticles(articles)

        }
    }
}