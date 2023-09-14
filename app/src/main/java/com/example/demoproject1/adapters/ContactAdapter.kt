package com.example.demoproject1.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.demoproject1.interfaces.ContactItemClickListener
import com.example.demoproject1.R
import com.example.demoproject1.databinding.ItemContactBinding
import com.example.demoproject1.models.Contact
import com.example.demoproject1.viewHolders.ContactViewHolder

class ContactAdapter (val context: Context, private val listener: ContactItemClickListener): RecyclerView.Adapter<ContactViewHolder>(){

    // region properties
    private val allContact = ArrayList<Contact>()

    // region lifecycle
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder.fromParent(parent)
    }
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(allContact[position], listener)
    }
    override fun getItemCount(): Int  = allContact.size

    // region private methods
    fun updateList(newList: List<Contact>){
        allContact.apply {
            clear()
            addAll(newList)
        }
        notifyDataSetChanged()
    }

}



// this should be added to viewholder class file