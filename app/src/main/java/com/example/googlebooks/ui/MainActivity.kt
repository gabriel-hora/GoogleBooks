package com.example.googlebooks.ui


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.googlebooks.databinding.ActivityMainBinding
import com.example.googlebooks.model.BookHttp
import com.example.googlebooks.model.Volume
import com.example.googlebooks.ui.adapter.BookListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val context = this

        //Carregar dados na UI com coroutines
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO){
                BookHttp.searchBook("Dominando o Android")
            }
            result?.items?.let {
                binding.recyclerView.adapter = BookListAdapter(it, context, this@MainActivity::openBookDetail)
            }
        }
    }

    private fun openBookDetail(volume: Volume) {
        val detailIntent = Intent(this, BookDetailActivity::class.java)
        detailIntent.putExtra("book", volume)
        startActivity(detailIntent)
    }
}