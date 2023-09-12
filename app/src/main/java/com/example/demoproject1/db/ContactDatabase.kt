package com.example.demoproject1.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.demoproject1.models.Contact

@Database(
    entities = [Contact::class],
    version = 1
)

 abstract class ContactDatabase : RoomDatabase() {

    abstract fun getContactDao(): ContactDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ContactDatabase? = null

        fun getDatabase(context: Context): ContactDatabase {
            //Singleton
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactDatabase::class.java,
                    "contact-database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}