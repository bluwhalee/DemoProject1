package com.example.demoproject1.viewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoproject1.R
import com.example.demoproject1.adapters.ContactAdapter
import com.example.demoproject1.models.Contact

class ContactViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    val contactName = itemView.findViewById<TextView>(R.id.contact_name)
    val contactNumber = itemView.findViewById<TextView>(R.id.contact_number)
    val deleteIcon = itemView.findViewById<ImageView>(R.id.delete_icon)

    fun bind(contact: Contact)
    {
        contactName.text = "${contact.firstName} ${contact.lastName}"
        contactNumber.text = contact.phoneNumber
    }
}