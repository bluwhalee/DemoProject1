package com.example.demoproject1.di

import android.content.Context
import androidx.room.Room
import androidx.transition.Visibility.Mode
import com.example.demoproject1.BuildConfig
import com.example.demoproject1.db.ContactDatabase
import com.example.demoproject1.retrofit.NewsApi
import com.example.demoproject1.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun getRetrofit():Retrofit{
        // add to gradle file
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun getNewsApi(retrofit: Retrofit):NewsApi{
        return retrofit.create(NewsApi::class.java)
    }
    @Provides
    @Singleton
    fun provideContactDatabase(@ApplicationContext appContext: Context): ContactDatabase {
        return Room.databaseBuilder(
            appContext,
            ContactDatabase::class.java,
            Constants.DatabaseConstants.DB_NAME
        ).build()
    }
}