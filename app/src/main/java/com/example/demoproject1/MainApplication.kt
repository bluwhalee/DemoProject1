package com.example.demoproject1

import android.app.Application
import android.content.Context
import com.example.demoproject1.db.ContactDatabase
import com.example.demoproject1.di.NetworkModule
import com.example.demoproject1.repository.ContactRepository
import com.example.demoproject1.repository.NewsRepository
import com.example.demoproject1.retrofit.NewsApi

class MainApplication : Application() {

    lateinit var newsRepository: NewsRepository
    lateinit var contactRepository: ContactRepository
    init {
        instance = this
    }

    companion object {

        var instance: MainApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        // initialize for any

        // Use ApplicationContext.
        // example: SharedPreferences etc...
        //context = applicationContext()
        val newsService = NetworkModule.getRetrofit().create(NewsApi::class.java)
        val database = ContactDatabase.getDatabase(applicationContext)
        newsRepository = NewsRepository(newsService)
        contactRepository = ContactRepository(database)

    }
}