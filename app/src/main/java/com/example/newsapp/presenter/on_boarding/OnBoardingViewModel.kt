package com.example.newsapp.presenter.on_boarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.App
import com.example.newsapp.repositories.on_boarding.OnBoardingRepository
import com.example.newsapp.repositories.on_boarding.OnBoardingRepositoryImpl
import com.example.newsapp.util.Constants.ON_BOARDING_SCREEN_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OnBoardingViewModel(private val repository: OnBoardingRepository) : ViewModel() {

    fun saveOnBoardingValue() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveValue(ON_BOARDING_SCREEN_KEY, true)
        }
    }

}