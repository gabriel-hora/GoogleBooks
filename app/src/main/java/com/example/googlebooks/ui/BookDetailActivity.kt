package com.example.googlebooks.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.googlebooks.R
import com.example.googlebooks.databinding.ActivityBookDetailBinding
import com.example.googlebooks.model.Volume
import com.example.googlebooks.repository.BookRepository
import com.example.googlebooks.ui.viewModel.BookDetailViewModel
import com.example.googlebooks.ui.viewModel.BookViewModelFactory
import com.squareup.picasso.Picasso

class BookDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookDetailBinding

    private val viewModel: BookDetailViewModel by lazy {
        ViewModelProvider(
            this,
            BookViewModelFactory(
                BookRepository(this)
            )
        )[BookDetailViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val volume = intent.getParcelableExtra<Volume>(EXTRA_BOOK)
        if (volume != null) {

            binding.txtTitle.text = volume.volumeInfo.title
            binding.txtAuthor.text = volume.volumeInfo.authors?.joinToString() ?: "-"
            binding.txtPages.text = volume.volumeInfo.pageCount?.toString() ?: "-"
            binding.txtDescription.text = volume.volumeInfo.description
            binding.txtPublisher.text = volume.volumeInfo.publisher

            if(volume.volumeInfo.imageLinks?.smallThumbnail != null) {
                Picasso.get().load(volume.volumeInfo.imageLinks?.smallThumbnail).into(binding.imageCover)
            } else {
                binding.imageCover.setImageResource(R.drawable.ic_baseline_broken_image_24)
            }

            viewModel.isFavorite.observe(
                this,
                Observer { isFavorite ->
                    if (isFavorite) {
                        binding.fabFavorite.setImageResource(R.drawable.ic_delete)
                        binding.fabFavorite.setOnClickListener {
                            viewModel.removeFromFavorite(volume)
                        }
                    } else {
                        binding.fabFavorite.setImageResource(R.drawable.ic_add)
                        binding.fabFavorite.setOnClickListener {
                            viewModel.saveToFavorites(volume)
                        }
                    }
                }
            )
        viewModel.onCreate(volume)
        } else {
            finish()
        }
    }

    companion object {
        private const val EXTRA_BOOK = "book"

        fun open(context: Context, volume: Volume) {
            val detailIntent = Intent(context, BookDetailActivity::class.java)
            detailIntent.putExtra(EXTRA_BOOK, volume)
            context.startActivity(detailIntent)
        }
    }
}