package com.example.demoproject1

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoproject1.databinding.FragmentSecondBinding
import com.example.demoproject1.di.NetworkModule
import com.example.demoproject1.models.Contact
import com.example.demoproject1.models.IContactRVAapter
import com.example.demoproject1.models.contactAdapter
import com.example.demoproject1.repository.NewsRepository
import com.example.demoproject1.retrofit.NewsApi

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() , IContactRVAapter{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentSecondBinding
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(layoutInflater)

        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        val adapter = contactAdapter(requireContext(), this)
        binding.recyclerview.adapter =adapter

        val newsRepository = (requireActivity().application as MainApplication).newsRepository
        val contactRepository = (requireActivity().application as MainApplication).contactRepository
        viewModel = ViewModelProvider(this,MainViewModelFactory(newsRepository,contactRepository)).get(MainViewModel::class.java)
        viewModel.allContacts.observe(viewLifecycleOwner, Observer {list->
            list?.let{
                adapter.updateList(it)
            }
        })


        return binding.root
    }

    override fun omItemClicked(contact: Contact) {
        viewModel.delete(contact)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SecondFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}