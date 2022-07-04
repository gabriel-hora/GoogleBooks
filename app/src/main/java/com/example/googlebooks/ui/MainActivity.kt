package com.example.googlebooks.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.googlebooks.databinding.ActivityMainBinding
import com.example.googlebooks.model.BookHttp
import com.example.googlebooks.ui.adapter.BookListAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val context = this

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        object: Thread() {
            override fun run() {
                super.run()
                val result = BookHttp.searchBook("Dominando o Android")

                runOnUiThread{
                    result?.items?.let {
                        binding.recyclerView.adapter = BookListAdapter(it, context)
                    }
                }
            }
        }.start()
    }
}