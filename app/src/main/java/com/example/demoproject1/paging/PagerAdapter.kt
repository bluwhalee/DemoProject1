package com.example.demoproject1.paging

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.demoproject1.fragments.FirstFragment
import com.example.demoproject1.fragments.SecondFragment
import com.example.demoproject1.fragments.ThirdFragment

class PagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
): FragmentStateAdapter(fragmentManager,lifecycle) {

    private val fragmentList = listOf<Fragment>(FirstFragment(), SecondFragment(), ThirdFragment())
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}