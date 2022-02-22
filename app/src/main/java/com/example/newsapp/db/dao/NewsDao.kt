package com.example.newsapp.db.dao

import androidx.room.*
import com.example.newsapp.model.Articles
import retrofit2.http.DELETE

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    suspend fun getAllNews(): List<Articles>

    @Query("SELECT url FROM news")
    suspend fun getNewsUrl(): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: Articles)

    @Query("DELETE FROM news WHERE url=:url")
    suspend fun deleteArticle(url:String)
}