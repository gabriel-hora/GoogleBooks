package com.example.googlebooks.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.googlebooks.databinding.ActivityBookDetailBinding.inflate
import com.example.googlebooks.databinding.ActivityMainBinding
import com.example.googlebooks.ui.fragments.BookListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val context = this
    }
}