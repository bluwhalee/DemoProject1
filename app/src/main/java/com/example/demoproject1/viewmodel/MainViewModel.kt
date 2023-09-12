package com.example.demoproject1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.demoproject1.models.Contact
import com.example.demoproject1.repository.ContactRepository
import com.example.demoproject1.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel (private val newsRepository: NewsRepository,private val contactRepository: ContactRepository): ViewModel() {
    val list = newsRepository.getNews().cachedIn(viewModelScope)

    val allContacts : LiveData<List<Contact>>
    init{
        allContacts = contactRepository.allContacts
    }
    fun delete(contact: Contact) = viewModelScope.launch(Dispatchers.IO ){
        contactRepository.delete(contact)
    }
    fun addContact(contact: Contact) = viewModelScope.launch(Dispatchers.IO ){
        contactRepository.insert(contact)
    }

}

class MainViewModelFactory(private val newsRepository: NewsRepository,private val contactRepository: ContactRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(newsRepository,contactRepository) as T
    }
}
