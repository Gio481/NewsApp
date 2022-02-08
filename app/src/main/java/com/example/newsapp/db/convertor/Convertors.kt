package com.example.newsapp.db.convertor

import androidx.room.TypeConverter
import com.example.newsapp.model.Articles
import com.example.newsapp.model.Source

class Convertors {
    @TypeConverter
    fun toString(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun fromString(name: String): Source {
        return Source(name = name)
    }
}