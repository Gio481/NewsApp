package com.example.newsapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@kotlinx.parcelize.Parcelize
@Entity(tableName = "news")
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
    val content: String?,
    var isFavorite: Boolean = false

) : Parcelable {
    fun getNewsPublishedTime(): String {
        return publishedAt.slice(0..9)
    }
}
