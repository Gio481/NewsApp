package com.example.newsapp.util.extensions.image_view

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.newsapp.R

fun ImageView.setImage(url: String?) {
    Glide.with(this).load(url).placeholder(R.drawable.default_news_photo).into(this)
}