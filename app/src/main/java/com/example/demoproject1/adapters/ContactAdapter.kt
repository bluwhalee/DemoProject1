package com.example.demoproject1.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoproject1.R
import com.example.demoproject1.models.Contact

class ContactAdapter (val context: Context, private val listener: ContactItemClickListener): RecyclerView.Adapter<ContactAdapter.ContactViewHolder>(){

    private val allContact = ArrayList<Contact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val viewHolder = ContactViewHolder(LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false))
        viewHolder.deleteIcon.setOnClickListener{
            listener.onItemClicked(allContact[viewHolder.absoluteAdapterPosition])
        }
        return viewHolder
    }
    // make it a single line

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val currentContact = allContact[position]
        holder.apply {
            contactName.text = "${currentContact.firstName} ${currentContact.lastName}"
            contactNumber.text = currentContact.phoneNumber
        }

    }
    // optimize this
    // do this inside viewholder class

    override fun getItemCount(): Int  = allContact.size

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val contactName = itemView.findViewById<TextView>(R.id.contact_name)
        val contactNumber = itemView.findViewById<TextView>(R.id.contact_number)
        val deleteIcon = itemView.findViewById<ImageView>(R.id.delete_icon)
    }
    // using data binding
    // make package for viewholder
    // make separate viewholder classes from adapter

    fun updateList(newList: List<Contact>){
        allContact.apply {
            clear()
            addAll(newList)
        }
        notifyDataSetChanged()
    }

}

interface ContactItemClickListener{
    fun onItemClicked(contact: Contact){

    }
}

// this should be added to viewholder class file