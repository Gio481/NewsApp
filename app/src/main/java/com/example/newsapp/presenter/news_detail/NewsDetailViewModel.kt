package com.example.newsapp.presenter.news_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.App
import com.example.newsapp.R
import com.example.newsapp.model.Articles
import com.example.newsapp.repositories.news_detail.NewsDetailRepository
import com.example.newsapp.repositories.news_detail.NewsDetailRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NewsDetailViewModel(private val repository: NewsDetailRepository) : ViewModel() {

    private val _backgroundLiveData: MutableLiveData<Int> = MutableLiveData()
    val backgroundLiveData: LiveData<Int> = _backgroundLiveData

    suspend fun checkSavedArticle(url: String): Boolean = url in repository.getNewsUrl()

    fun determineOperation(articles: Articles, url: String) {
        viewModelScope.launch {
            if (checkSavedArticle(url)) {
                deleteArticles(url)
            } else {
                insertArticle(articles)
            }
        }
    }

    private suspend fun insertArticle(articles: Articles) {
        repository.insertArticles(articles)
    }

    private suspend fun deleteArticles(url: String) {
        repository.deleteArticle(url)
    }

    fun checkArticle(url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (checkSavedArticle(url)) {
                _backgroundLiveData.postValue(R.drawable.ic_delete)
            } else {
                _backgroundLiveData.postValue(R.drawable.ic_favorite)
            }
        }
    }
}