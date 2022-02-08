package com.example.newsapp.model

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class Source(
    val id: String? = null,
    val name: String
) : Parcelable