package com.example.demoproject1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.demoproject1.MainApplication
import com.example.demoproject1.viewmodel.MainViewModel
import com.example.demoproject1.R
import com.example.demoproject1.adapters.NewsPagingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var paggingAdapter: NewsPagingAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        setRecycler(view)
        return view
    }
    fun setRecycler(view: View){
        recyclerView = view.findViewById(R.id.recyclerView)
        paggingAdapter = NewsPagingAdapter()

        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            recyclerView.setHasFixedSize(true)
            adapter = paggingAdapter
        }

        mainViewModel.list.observe(viewLifecycleOwner, Observer {
            it?.let {
                paggingAdapter.submitData(lifecycle, it)
            }

        })
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {}
    }
}