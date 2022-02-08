package com.example.newsapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

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
    val content: String?
) :Parcelable
