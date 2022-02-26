package com.example.newsapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.model.Articles
import com.example.newsapp.db.convertor.Convertors
import com.example.newsapp.db.dao.NewsDao

@Database(entities = [Articles::class], version = 1)
@TypeConverters(Convertors::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao():NewsDao
}