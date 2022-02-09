package com.example.newsapp.ui.news_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.App
import com.example.newsapp.model.Articles
import com.example.newsapp.repository.news_detail.NewsDetailRepository
import com.example.newsapp.repository.news_detail.NewsDetailRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsDetailViewModel : ViewModel() {
    private val repository: NewsDetailRepository by lazy { NewsDetailRepositoryImpl(App.db.newsDao()) }
    private val _isFavoriteItemLiveData: MutableLiveData<List<String>> = MutableLiveData()
    val urlList: LiveData<List<String>> = _isFavoriteItemLiveData

    init {
        viewModelScope.launch {
            _isFavoriteItemLiveData.postValue(repository.getNewsUrl())
        }
    }

    fun determineOperation(articles: Articles) {
        if (!articles.isFavorite) {
            insertArticle(articles)
        } else {
            deleteArticles(articles)
        }
    }

    private fun insertArticle(articles: Articles) {
        articles.isFavorite = !articles.isFavorite
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertArticles(articles)
        }
    }

    private fun deleteArticles(articles: Articles) {
        articles.isFavorite = !articles.isFavorite
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteArticle(articles)
            repository.updateArticle(articles)

        }
    }
}