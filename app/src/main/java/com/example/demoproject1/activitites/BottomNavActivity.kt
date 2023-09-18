package com.example.demoproject1.activitites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.demoproject1.paging.PagerAdapter
import com.example.demoproject1.R
import com.example.demoproject1.databinding.ActivityBottomNavBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomNavActivity : AppCompatActivity() {

    // region properties
    private  lateinit var binding: ActivityBottomNavBinding
    private lateinit var adapter: PagerAdapter

    // region lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    // region private methods
    private fun init() {
        adapter = PagerAdapter(supportFragmentManager, lifecycle)
        binding.mainViewpager.adapter = adapter
        setupBottomNav()
        setupViewPager()
    }
    private fun setupBottomNav(){
        binding.apply {
            mainViewpager.apply {
                bottomNavigationView.setOnItemSelectedListener{
                    currentItem = when (it.itemId) {
                        R.id.miMessages -> 1
                        R.id.miProfile -> 2
                        else -> 0
                    }
                    true
                }
            }
        }
    }
    // optimize
    private fun setupViewPager(){
        binding.apply {
            mainViewpager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    bottomNavigationView.apply {
                        selectedItemId = menu.getItem(position).itemId
                    }
                }
            })
        }
    }
}