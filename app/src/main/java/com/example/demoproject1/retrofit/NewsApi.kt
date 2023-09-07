package com.example.demoproject1.retrofit

import com.example.demoproject1.models.News
import retrofit2.http.GET
import retrofit2.http.Query


const val API_KEY = "d878d732682c4e548fd61b035912f1db"

interface NewsApi {

    @GET("v2/top-headlines?apiKey=$API_KEY&country=us")
    suspend fun getNews(@Query("page") page: Int): News
}