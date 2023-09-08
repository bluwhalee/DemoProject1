package com.example.demoproject1.repository

import androidx.lifecycle.LiveData
import com.example.demoproject1.db.ContactDao
import com.example.demoproject1.db.ContactDatabase
import com.example.demoproject1.models.Contact
import javax.inject.Inject
import javax.inject.Singleton

class ContactRepository (private val contactDatabase: ContactDatabase) {

    val allContacts: LiveData<List<Contact>> = contactDatabase.getContactDao().getContacts()
    suspend fun insert(contact: Contact)
    {
        contactDatabase.getContactDao().upsertContact(contact)
    }
    suspend fun delete(contact: Contact)
    {
        contactDatabase.getContactDao().deleteContact(contact)
    }
}