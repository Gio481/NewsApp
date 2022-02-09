package com.example.newsapp.ui.on_boarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.App
import com.example.newsapp.repository.on_boarding.OnBoardingRepository
import com.example.newsapp.repository.on_boarding.OnBoardingRepositoryImpl
import com.example.newsapp.util.Constants.ON_BOARDING_SCREEN_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OnBoardingViewModel : ViewModel() {
    private val repository: OnBoardingRepository by lazy { OnBoardingRepositoryImpl(App.dataStore) }
    private var _onBoardingScreenLiveData: MutableLiveData<Int> = MutableLiveData(0)
    val onBoardingScreenLiveData: LiveData<Int> = _onBoardingScreenLiveData

    fun onBoardingScreen() {
        _onBoardingScreenLiveData.value = _onBoardingScreenLiveData.value?.plus(1)
    }

    init {
        saveValue()
    }
    private fun saveValue() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveValue(ON_BOARDING_SCREEN_KEY, true)
        }
    }

}