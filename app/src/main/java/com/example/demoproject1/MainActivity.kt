package com.example.demoproject1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.demoproject1.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.tabBtn.setOnClickListener{
            val intent =  Intent(this, TabLayoutActivity::class.java)
            startActivity(intent)
        }
        binding.btnNav.setOnClickListener{
            val intent =  Intent(this, BottomNavActivity::class.java)
            startActivity(intent)
        }



    }


}