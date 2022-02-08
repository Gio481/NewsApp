package com.example.newsapp.ui.search_news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.api.RetrofitInstance.API_KEY
import com.example.newsapp.model.NewsResponse
import com.example.newsapp.repository.search_news.SearchNewsRepository
import com.example.newsapp.repository.search_news.SearchNewsRepositoryImpl
import com.example.newsapp.util.Resources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchNewsViewModel : ViewModel() {
    private val repository: SearchNewsRepository by lazy { SearchNewsRepositoryImpl() }
    private val _successNewsLiveData: MutableLiveData<NewsResponse> = MutableLiveData()
    val successNewsLiveData: LiveData<NewsResponse> get() = _successNewsLiveData

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String> get() = _errorLiveData

    fun searchNews(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = repository.getSearchedNews(1, API_KEY, text)) {
                is Resources.Success -> _successNewsLiveData.postValue(response.data!!)
                is Resources.Error -> _errorLiveData.postValue(response.message!!)
            }
        }
    }
}