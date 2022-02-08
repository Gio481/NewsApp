package com.example.newsapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.model.Articles

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    suspend fun getAllNews(): List<Articles>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: Articles)
}