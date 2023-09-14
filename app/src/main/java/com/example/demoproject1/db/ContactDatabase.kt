package com.example.demoproject1.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.demoproject1.models.Contact
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Database(
    entities = [Contact::class],
    version = 1
)
 abstract class ContactDatabase : RoomDatabase() {
    abstract fun getContactDao(): ContactDao
}