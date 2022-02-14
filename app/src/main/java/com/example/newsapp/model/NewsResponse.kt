package com.example.newsapp.model


data class NewsResponse(
    val getStatus: String,
    val totalResults: Int,
    val articles: List<Articles>
)

