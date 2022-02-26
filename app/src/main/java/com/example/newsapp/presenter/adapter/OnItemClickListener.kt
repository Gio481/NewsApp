package com.example.newsapp.presenter.adapter

import com.example.newsapp.model.Articles

interface OnItemClickListener {
    fun onItemCLick(article: Articles)
}