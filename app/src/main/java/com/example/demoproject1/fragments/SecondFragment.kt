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
import com.example.demoproject1.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondFragment : Fragment() , ContactItemClickListener {

    // region properties
    private lateinit var binding: FragmentSecondBinding
    private lateinit var dialogBinding: ContactDialogBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var dialog: Dialog
    private lateinit var recyclerAdapter: ContactAdapter

    // region lifecycle
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(layoutInflater)
        dialogBinding = ContactDialogBinding.inflate(layoutInflater)
        init()
        return binding.root
    }

    // region private methods
    private fun init(){
        setRecyclerView()
        setViewModel()
        setAllContactObserver()
        setFAB()
    }
    private fun setFAB(){
        binding.contactFAB.setOnClickListener{
            popDialog()
        }
    }
    private fun setRecyclerView(){
        recyclerAdapter = ContactAdapter(this)
        binding.contactRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerAdapter
        }
    }
    private fun setViewModel() {
        //TODO(separation and remove dependency)
       viewModel = ViewModelProvider(
           requireActivity()
       )[MainViewModel::class.java]
    }

    private fun setAllContactObserver(){
        viewModel.getAllContacts().observe(viewLifecycleOwner, Observer {list->
            list?.let{
                recyclerAdapter.updateList(it)
            }
        })
    }
    private fun popDialog(){
        //TODO(apply view/databinding)
        context?.let {parentContext->
            dialog = Dialog(parentContext)
            dialog.apply {
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setContentView(dialogBinding.root)
                dialogBinding.btnSave.setOnClickListener{
                    val firstName = dialogBinding.edtFirstName.text.toString()
                    val lastName = dialogBinding.edtLastName.text.toString()
                    val phoneNumber = dialogBinding.edtPhoneNumber.text.toString()

                    // early return
                    if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty()){
                        // string resource
                        Toast.makeText(parentContext, Constants.random.empty_fields, Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                    viewModel.addContact(Contact(0, firstName, lastName, phoneNumber))
                    dismiss()
                }
                dialogBinding.btnCancel.setOnClickListener{
                    dismiss()
                }

                show()
            }
        }
    }

    // region implemented
    override fun onItemClicked(contact: Contact) {
        viewModel.delete(contact)
    }
    // end region
}