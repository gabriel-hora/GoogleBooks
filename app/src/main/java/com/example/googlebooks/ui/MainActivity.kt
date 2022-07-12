package com.example.googlebooks.ui


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.googlebooks.R
import com.example.googlebooks.databinding.ActivityMainBinding
import com.example.googlebooks.model.BookHttp
import com.example.googlebooks.model.Volume
import com.example.googlebooks.ui.adapter.BookListAdapter
import com.example.googlebooks.ui.viewModel.BookListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: BookListViewModel by lazy {
        ViewModelProvider(this)[BookListViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val context = this

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.state.observe(this, Observer { state ->
            when(state) {
                is BookListViewModel.State.Loading -> {
                    //Não está resgatando o atributo correto
                    binding.vwLoading.visibility = View.VISIBLE
                }
                is BookListViewModel.State.Loaded -> {
                    binding.vwLoading.visibility = View.GONE
                    binding.recyclerView.adapter = BookListAdapter(state.items, context, this@MainActivity::openBookDetail)
                }
                is BookListViewModel.State.Error -> {
                    binding.vwLoading.visibility = View.GONE
                    if(!state.hasConsumed) {
                        state.hasConsumed = true
                        Toast.makeText(this@MainActivity, R.string.error_loading, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
        viewModel.loadBooks()
    }

    private fun openBookDetail(volume: Volume) {
        val detailIntent = Intent(this, BookDetailActivity::class.java)
        detailIntent.putExtra("book", volume)
        startActivity(detailIntent)
    }
}