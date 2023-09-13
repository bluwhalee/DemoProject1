package com.example.demoproject1.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.demoproject1.MainApplication
import com.example.demoproject1.viewmodel.MainViewModel
import com.example.demoproject1.R
import com.example.demoproject1.databinding.ContactDialogBinding
import com.example.demoproject1.databinding.FragmentSecondBinding
import com.example.demoproject1.models.Contact
import com.example.demoproject1.adapters.IContactRVAapter
import com.example.demoproject1.adapters.contactAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondFragment : Fragment() , IContactRVAapter {

    lateinit var binding: FragmentSecondBinding
    lateinit var dialogBinding: ContactDialogBinding
    lateinit var viewModel: MainViewModel
    lateinit var dialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(layoutInflater)
        dialogBinding = ContactDialogBinding.inflate(layoutInflater)

        val recyclerAdapter = contactAdapter(requireContext(), this)
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerAdapter
        }

        setViewModel(recyclerAdapter)


        binding.fab.setOnClickListener{
            setDialog()
        }
        return binding.root
    }

    private fun setViewModel(recyclerAdapter : contactAdapter) {
       viewModel = ViewModelProvider(
           requireActivity()
        ).get(MainViewModel::class.java)
        viewModel.allContacts.observe(viewLifecycleOwner, Observer {list->
            list?.let{
                recyclerAdapter.updateList(it)
            }
        })
    }

    private fun setDialog(){
        dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.contact_dialog)

        dialog.findViewById<Button>(R.id.btnSave).setOnClickListener{
            var firstName = dialog.findViewById<EditText>(R.id.dgFirstName).text.toString()
            var lastName = dialog.findViewById<EditText>(R.id.dgLastName).text.toString()
            var phoneNumber = dialog.findViewById<EditText>(R.id.dgPhoneNumber).text.toString()

            if (firstName!="" || lastName!="" || phoneNumber!="")
            {
                var contact = Contact(0, firstName, lastName, phoneNumber)
                viewModel.addContact(contact)
                dialog.dismiss()
            }
            else{
                Toast.makeText(requireContext(), "Field(s) Empty", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.findViewById<Button>(R.id.btnCancel).setOnClickListener{
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun omItemClicked(contact: Contact) {
        viewModel.delete(contact)
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {}
    }
}