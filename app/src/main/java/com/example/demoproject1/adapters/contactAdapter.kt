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

class contactAdapter (val context: Context, private val listener: IContactRVAapter): RecyclerView.Adapter<contactAdapter.ContactViewHolder>(){

    val allContact = ArrayList<Contact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val viewHolder = ContactViewHolder(LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false))
        viewHolder.deleteIcon.setOnClickListener{
            listener.omItemClicked(allContact[viewHolder.absoluteAdapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val currentContact = allContact[position]
        holder.apply {
            contactName.text = "${currentContact.firstName} ${currentContact.lastName}"
            contactNumber.text = currentContact.phoneNumber
        }

    }

    override fun getItemCount(): Int {
        return allContact.size
    }

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val contactName = itemView.findViewById<TextView>(R.id.contactName)
        val contactNumber = itemView.findViewById<TextView>(R.id.contactNumber)
        val deleteIcon = itemView.findViewById<ImageView>(R.id.deleteIcon)
    }

    fun updateList(newList: List<Contact>){
        allContact.clear()
        allContact.addAll(newList)
        notifyDataSetChanged()
    }

}

interface IContactRVAapter{
    fun omItemClicked(contact: Contact){

    }
}