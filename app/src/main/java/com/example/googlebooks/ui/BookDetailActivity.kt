package com.example.googlebooks.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.googlebooks.R
import com.example.googlebooks.databinding.ActivityBookDetailBinding
import com.example.googlebooks.databinding.ActivityMainBinding
import com.example.googlebooks.model.Volume
import com.squareup.picasso.Picasso

class BookDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookDetailBinding

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

            if(volume.volumeInfo.imageLinks.smallThumbnail != null) {
                Picasso.get().load(volume.volumeInfo.imageLinks.smallThumbnail).into(binding.imageCover)
            } else {
                binding.imageCover.setImageResource(R.drawable.ic_baseline_broken_image_24)
            }

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