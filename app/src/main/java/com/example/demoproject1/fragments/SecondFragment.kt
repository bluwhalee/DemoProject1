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
import com.example.demoproject1.viewmodel.MainViewModel
import com.example.demoproject1.R
import com.example.demoproject1.databinding.ContactDialogBinding
import com.example.demoproject1.databinding.FragmentSecondBinding
import com.example.demoproject1.models.Contact
import com.example.demoproject1.adapters.ContactAdapter
import com.example.demoproject1.interfaces.ContactItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondFragment : Fragment() , ContactItemClickListener {

    // region properties
    lateinit var binding: FragmentSecondBinding
    lateinit var dialogBinding: ContactDialogBinding
    lateinit var viewModel: MainViewModel
    lateinit var dialog: Dialog
    lateinit var recyclerAdapter: ContactAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // region lifecycle
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(layoutInflater)
        init()
        return binding.root
    }

    // region private methods
    private fun init(){
        setRecyclerView()
        setViewModel(recyclerAdapter)
        binding.contactFAB.setOnClickListener{
            setDialog()
        }
    }
    private fun setRecyclerView(){
        recyclerAdapter = ContactAdapter(requireContext(), this)
        binding.contactRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerAdapter
        }
    }
    private fun setViewModel(recyclerAdapter : ContactAdapter) {
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
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.contact_dialog)
            findViewById<Button>(R.id.btn_save).setOnClickListener{
                var firstName = findViewById<EditText>(R.id.edt_first_name).text.toString()
                var lastName = findViewById<EditText>(R.id.edt_last_name).text.toString()
                var phoneNumber = findViewById<EditText>(R.id.edt_phone_number).text.toString()

                if (firstName!="" || lastName!="" || phoneNumber!="")
                {
                    var contact = Contact(0, firstName, lastName, phoneNumber)
                    viewModel.addContact(contact)
                    dismiss()
                }
                else{
                    Toast.makeText(requireContext(), "Field(s) Empty", Toast.LENGTH_SHORT).show()
                }
            }
            findViewById<Button>(R.id.btn_cancel).setOnClickListener{
                dismiss()
            }

            show()
        }
    }

    // region implemented
    override fun onItemClicked(contact: Contact) {
        viewModel.delete(contact)
    }
    // end region
}