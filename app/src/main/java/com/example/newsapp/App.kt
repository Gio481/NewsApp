package com.example.newsapp

import android.app.Application
import com.example.newsapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                RepositoryModule.repositoryModule,
                NetworkModule.retrofitModule,
                RoomDatabaseModule.databaseModule,
                DatastoreModule.dataStoreModule,
                ViewModelModule.viewModelModule
            )
        }
    }
}
