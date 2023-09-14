package com.example.demoproject1.interfaces

import com.example.demoproject1.models.Contact

interface ContactItemClickListener{
    fun onItemClicked(contact: Contact){}
}