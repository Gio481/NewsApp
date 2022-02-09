package com.example.newsapp.db.dao

import androidx.room.*
import com.example.newsapp.model.Articles

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    suspend fun getAllNews(): List<Articles>

    @Query("SELECT url from news")
    suspend fun getNewsUrl(): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: Articles)

    @Update
    suspend fun updateArticle(articles: Articles)

    @Delete
    suspend fun deleteArticle(articles: Articles)
}