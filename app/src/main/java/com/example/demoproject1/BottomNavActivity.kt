package com.example.demoproject1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.demoproject1.databinding.ActivityBottomNavBinding
import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomNavActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityBottomNavBinding
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: PagerAdapter
    private lateinit var bottomNav: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNav = binding.bottomNavigationView
        adapter = PagerAdapter(supportFragmentManager, lifecycle)
        viewPager2 = binding.ViewPager
        viewPager2.adapter = adapter

//        val firstFragment = FirstFragment()
//        val secondFragment = SecondFragment()
//        val thirdFragment = ThirdFragment()
////        setFragment(firstFragment)

        binding.bottomNavigationView.setOnItemSelectedListener{
            when(it.itemId){
                R.id.miHome -> viewPager2.currentItem = 0
                R.id.miMessages -> viewPager2.currentItem = 1
                R.id.miProfile -> viewPager2.currentItem = 2
            }
            true
        }
        viewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val menuItem = bottomNav.menu.getItem(position)
                bottomNav.selectedItemId = menuItem.itemId
            }
        })
    }

//    private fun setFragment(fragment: Fragment)
//    {
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.flFragment.id, fragment)
//            commit()
//        }
//    }
}