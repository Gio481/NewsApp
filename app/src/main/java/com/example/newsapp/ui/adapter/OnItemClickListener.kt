package com.example.newsapp.ui.adapter

import com.example.newsapp.model.Articles
import com.example.newsapp.ui.news.adapter.Category

interface OnItemClickListener {
    fun onItemCLick(article: Articles)
}