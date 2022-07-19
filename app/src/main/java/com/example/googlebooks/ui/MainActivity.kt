package com.example.googlebooks.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.googlebooks.R
import com.example.googlebooks.databinding.ActivityMainBinding
import com.example.googlebooks.ui.adapter.BookPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val context = this
        setSupportActionBar(binding.toolbar)

        binding.viewPager.adapter = BookPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.setText(
                if (position == 0) {
                    R.string.tab_books
                } else {
                    R.string.tab_favorites
                }
            )
        }.attach()
    }
}