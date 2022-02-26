package com.example.newsapp.presenter.saved_news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.App
import com.example.newsapp.model.Articles
import com.example.newsapp.repositories.save_news.SaveNewsRepository
import com.example.newsapp.repositories.save_news.SaveNewsRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavedNewsViewModel(private val repository: SaveNewsRepository) : ViewModel() {

    private val _allNewsLiveData: MutableLiveData<List<Articles>> = MutableLiveData()
    val allNewsLiveData: LiveData<List<Articles>> get() = _allNewsLiveData

     fun getAllNews() {
        viewModelScope.launch(Dispatchers.IO) {
            _allNewsLiveData.postValue(repository.gelAllNews())
        }
    }

}