package com.example.demoproject1.viewHolders

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoproject1.R
import com.example.demoproject1.adapters.ContactAdapter
import com.example.demoproject1.databinding.ItemContactBinding
import com.example.demoproject1.databinding.NewsItemBinding
import com.example.demoproject1.interfaces.ContactItemClickListener
import com.example.demoproject1.models.Contact

class ContactViewHolder (val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(contact: Contact, listener: ContactItemClickListener )
    {
        binding.apply {
            this.contact = contact
            this.listener = listener
            executePendingBindings()
        }
    }

    companion object{
        fun fromParent(parent: ViewGroup,):ContactViewHolder{
            return ContactViewHolder(ItemContactBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }
    }
}
