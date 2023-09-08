package com.example.demoproject1.di

import androidx.transition.Visibility.Mode
import com.example.demoproject1.retrofit.NewsApi
import com.example.demoproject1.utils.Constants.BASE_URl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


object NetworkModule {

    fun getRetrofit():Retrofit{
        return Retrofit.Builder().baseUrl(BASE_URl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}