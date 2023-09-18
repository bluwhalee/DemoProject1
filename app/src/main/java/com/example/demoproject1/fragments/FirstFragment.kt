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
import com.example.demoproject1.adapters.NewsPagingAdapter
import com.example.demoproject1.databinding.FragmentFirstBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : Fragment() {

    // region properties
    private lateinit var pagingAdapter: NewsPagingAdapter
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: FragmentFirstBinding


    // region lifecycle
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(layoutInflater)
        init()
        return binding.root
    }

    // region private methods
    private fun init(){
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        pagingAdapter = NewsPagingAdapter()
        setRecycler()
        observeNewsList()
    }
    private fun setRecycler(){
        //TODO(remove dependency)

        binding.newsRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = pagingAdapter
        }
        // observe each live data in separate methods
    }
    private fun observeNewsList()
    {
        mainViewModel.getNews().observe(viewLifecycleOwner, Observer {
            it?.let {
                pagingAdapter.submitData(lifecycle, it)
            }
        })
    }
}