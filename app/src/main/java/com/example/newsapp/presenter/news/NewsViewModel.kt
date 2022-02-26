package com.example.newsapp.presenter.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.model.NewsResponse
import com.example.newsapp.repositories.news.NewsRepository
import com.example.newsapp.repositories.news.NewsRepositoryImpl
import com.example.newsapp.util.Resources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {

    private val _successNewsLiveData: MutableLiveData<NewsResponse> = MutableLiveData()
    val successNewsLiveData: LiveData<NewsResponse> get() = _successNewsLiveData

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: MutableLiveData<String> get() = _errorLiveData

    private var pageNumber = 1

    fun getCustomCategoryNews(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response =
                repository.getCustomCategoryNews(category = category, page = pageNumber)) {
                is Resources.Success -> _successNewsLiveData.postValue(response.data!!)
                is Resources.Error -> _errorLiveData.postValue(response.message!!)
            }
        }
    }
}