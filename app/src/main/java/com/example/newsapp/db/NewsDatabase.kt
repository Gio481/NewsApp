package com.example.newsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.db.dao.NewsDao
import com.example.newsapp.model.Articles
import com.example.newsapp.db.convertor.Convertors

@Database(entities = [Articles::class], version = 1)
@TypeConverters(Convertors::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {
        fun buildNewsDatabase(context: Context): NewsDatabase = Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            NEWS_DATABASE_NAME
        ).build()

        private const val NEWS_DATABASE_NAME = "news.db"
        const val NEWS_DATABASE_TABLE_NAME = "news"
    }

}