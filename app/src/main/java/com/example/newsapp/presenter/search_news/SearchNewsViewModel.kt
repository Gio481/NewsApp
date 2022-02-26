package com.example.newsapp.presenter.search_news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.model.NewsResponse
import com.example.newsapp.repositories.search_news.SearchNewsRepository
import com.example.newsapp.repositories.search_news.SearchNewsRepositoryImpl
import com.example.newsapp.util.Resources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchNewsViewModel(private val repository: SearchNewsRepository) : ViewModel() {

    private val _successNewsLiveData: MutableLiveData<NewsResponse> = MutableLiveData()
    val successNewsLiveData: LiveData<NewsResponse> get() = _successNewsLiveData

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String> get() = _errorLiveData

    private var currentPage = 1

    fun searchNews(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (text.isNotBlank()) {
                when (val response = repository.getSearchedNews(currentPage, text)) {
                    is Resources.Success -> _successNewsLiveData.postValue(response.data!!)
                    is Resources.Error -> _errorLiveData.postValue(response.message!!)
                }
            }
        }
    }
}