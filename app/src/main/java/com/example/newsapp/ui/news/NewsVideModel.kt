package com.example.newsapp.ui.news

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

class NewsVideModel : ViewModel() {
    private val repository: NewsRepository by lazy { NewsRepositoryImpl() }

    private val _successNewsLiveData: MutableLiveData<NewsResponse> = MutableLiveData()
    val successNewsLiveData: LiveData<NewsResponse> get() = _successNewsLiveData

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: MutableLiveData<String> get() = _errorLiveData

    private val _categoryLiveData: MutableLiveData<String> = MutableLiveData()
    val categoryLiveData: LiveData<String> = _categoryLiveData

    private var pageNumber = 1

    init {
        _categoryLiveData.postValue(DEFAULT_API_CATEGORY)
        getCustomCategoryNews(DEFAULT_API_CATEGORY)
    }

    fun saveCategory(text: String) {
        _categoryLiveData.postValue(text)
    }

    fun getCustomCategoryNews(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response =
                repository.getCustomCategoryNews(pageNumber, category = category)) {
                is Resources.Success -> _successNewsLiveData.postValue(response.data!!)
                is Resources.Error -> _errorLiveData.postValue(response.message!!)
            }
        }
    }

    companion object {
        private const val DEFAULT_API_CATEGORY = "business"
    }
}