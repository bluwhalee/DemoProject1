package com.example.demoproject1.utils

import com.example.demoproject1.BuildConfig
import java.io.File
import java.io.FileInputStream
import java.util.Properties


object Constants {
    const val BASE_URl = "https://newsapi.org/"
    const val NEWS_ENDPOINT = "v2/top-headlines?apiKey=${BuildConfig.key}&country=us"
}