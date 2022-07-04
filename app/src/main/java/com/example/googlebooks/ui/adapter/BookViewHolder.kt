package com.example.googlebooks.ui.adapter


import androidx.recyclerview.widget.RecyclerView
import com.example.googlebooks.databinding.ActivityMainBinding
import com.example.googlebooks.databinding.ItemBookBinding

class BookViewHolder(binding: ItemBookBinding): RecyclerView.ViewHolder(binding.root) {

    val imageCover = binding.imageCover
    val txtTitle = binding.txtTitle
    val txtAuthor = binding.txtAuthor
    val txtPages = binding.txtPages

}