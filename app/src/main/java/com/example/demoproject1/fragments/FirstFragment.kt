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
import com.example.demoproject1.viewmodel.MainViewModel
import com.example.demoproject1.R
import com.example.demoproject1.adapters.NewsPagingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : Fragment() {
    // viewbinding or databinding

    // region properties
    private lateinit var paggingAdapter: NewsPagingAdapter
    private lateinit var mainViewModel: MainViewModel
    private lateinit var recyclerView : RecyclerView
    // end region

    // region lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
    // end region

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        init()
        setRecycler(view)
        return view
    }

    // region private methods
    private fun init(){
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        paggingAdapter = NewsPagingAdapter()
    }
    private fun setRecycler(view: View){
        recyclerView = view.findViewById<RecyclerView>(R.id.news_recyclerview)

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
        // observe each live data in separate methods
    }

    // end region

    // region callbacks
}