package com.example.newsapp.di

import android.content.Context
import androidx.room.Room
import com.example.newsapp.db.NewsDatabase
import org.koin.dsl.module

object RoomDatabaseModule {

    private fun provideDatabase(context: Context): NewsDatabase =
        Room.databaseBuilder(context, NewsDatabase::class.java, NEWS_DATABASE_NAME).build()

    private fun provideDao(db: NewsDatabase) = db.newsDao()

    val databaseModule = module {
        single { provideDatabase(get()) }
        single { provideDao(get()) }
    }

    private const val NEWS_DATABASE_NAME = "news.db"
    const val NEWS_DATABASE_TABLE_NAME = "news"
}