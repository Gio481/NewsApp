package com.example.newsapp.ui.splash_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.App
import com.example.newsapp.repositories.splash_screen.SplashScreenRepository
import com.example.newsapp.repositories.splash_screen.SplashScreenRepositoryImpl
import com.example.newsapp.util.Constants.ON_BOARDING_SCREEN_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {
    private val repository: SplashScreenRepository by lazy { SplashScreenRepositoryImpl(App.dataStore) }

    private val _splashScreenLiveData: MutableLiveData<String> = MutableLiveData()
    val splashScreenLiveData: LiveData<String> = _splashScreenLiveData

     fun getValue() {
        viewModelScope.launch(Dispatchers.IO) {
            _splashScreenLiveData.postValue(repository.getValue(ON_BOARDING_SCREEN_KEY).first())
        }
    }
}