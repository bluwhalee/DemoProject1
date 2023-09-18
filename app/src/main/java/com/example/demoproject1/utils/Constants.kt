package com.example.demoproject1.utils

import com.example.demoproject1.BuildConfig
import java.io.File
import java.io.FileInputStream
import java.util.Properties


object Constants {
    object APIConstant{
        const val NEWS_ENDPOINT = "v2/top-headlines?apiKey=${BuildConfig.key}&country=us"
        const val PAGE = "page"
    }

    object DatabaseConstants{
        const val DB_NAME = "contact-database"

    }
    object gallery {
        const val imageType = "image/*"
    }

    object numbers {
        const val one =1
    }
    object random {
        const val empty_fields = "Field(s) Empty"
    }
}