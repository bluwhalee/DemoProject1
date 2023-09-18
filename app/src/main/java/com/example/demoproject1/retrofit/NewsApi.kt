package com.example.demoproject1.retrofit

import com.android.volley.BuildConfig
import com.example.demoproject1.models.News
import com.example.demoproject1.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET(Constants.APIConstant.NEWS_ENDPOINT)
    suspend fun getNews(@Query(Constants.APIConstant.PAGE) page: Int): News
}