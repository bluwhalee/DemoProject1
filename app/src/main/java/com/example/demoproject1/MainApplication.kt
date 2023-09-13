package com.example.demoproject1

import android.app.Application
import android.content.Context
import com.example.demoproject1.db.ContactDatabase
import com.example.demoproject1.di.NetworkModule
import com.example.demoproject1.repository.ContactRepository
import com.example.demoproject1.repository.NewsRepository
import com.example.demoproject1.retrofit.NewsApi
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {
}