package com.example.newsapp

import android.app.Application
import com.example.newsapp.datastore.OnBoardingDataStore
import com.example.newsapp.db.NewsDatabase

class App : Application() {

    companion object {
        private lateinit var context: App
        val db: NewsDatabase by lazy {
            NewsDatabase.buildNewsDatabase(context)
        }
        val dataStore:OnBoardingDataStore by lazy {
            OnBoardingDataStore(context)
        }

    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}
