package com.example.newsapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapp.db.NewsDatabase.Companion.NEWS_DATABASE_TABLE_NAME
import com.google.gson.annotations.SerializedName

@kotlinx.parcelize.Parcelize
@Entity(tableName = NEWS_DATABASE_TABLE_NAME)
data class Articles(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val source: Source,
    val author: String?,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String? = null,
    val publishedAt: String,
    val content: String?
) : Parcelable {
    fun getNewsPublishedTime(): String {
        return publishedAt.slice(0..9)
    }
}
