package com.example.demoproject1.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.demoproject1.models.Contact
@Dao
interface ContactDao {

    @Upsert
    suspend fun upsertContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("Select * FROM contact ORDER BY id DESC")
    fun getContacts() : LiveData<List<Contact>>

}