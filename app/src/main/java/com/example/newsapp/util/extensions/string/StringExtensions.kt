package com.example.newsapp.util.extensions.string

import android.content.Context
import android.widget.Toast

fun String.showToast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}