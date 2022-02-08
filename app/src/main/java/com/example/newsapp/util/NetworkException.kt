package com.example.newsapp.util

import java.io.IOException

class NetworkException:IOException() {

    override val message: String
        get() = NO_INTERNET_CONNECTION

    companion object{
        private const val NO_INTERNET_CONNECTION= "No internet connection"
    }
}
