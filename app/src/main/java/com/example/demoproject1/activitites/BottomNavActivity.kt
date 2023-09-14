package com.example.demoproject1.activitites

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.example.demoproject1.BuildConfig
import com.example.demoproject1.paging.PagerAdapter
import com.example.demoproject1.R
import com.example.demoproject1.databinding.ActivityBottomNavBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.EntryPoint
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
        initActivity()
        setupBottomNav()
    }

    // region private methods
    private fun initActivity() {

        adapter = PagerAdapter(supportFragmentManager, lifecycle)
        binding.mainViewpager.adapter = adapter


    }
    private fun setupBottomNav(){
        binding.apply {
            mainViewpager.apply {
                bottomNavigationView.setOnItemSelectedListener{
                    when(it.itemId){
                        R.id.miHome -> currentItem = 0
                        R.id.miMessages -> mainViewpager.currentItem = 1
                        R.id.miProfile -> mainViewpager.currentItem = 2
                    }
                    true
                }
            }

        }
    }
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