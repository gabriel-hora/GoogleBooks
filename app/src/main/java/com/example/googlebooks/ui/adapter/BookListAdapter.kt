package com.example.googlebooks.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.googlebooks.R
import com.example.googlebooks.databinding.ItemBookBinding
import com.example.googlebooks.model.Volume
import com.squareup.picasso.Picasso

class BookListAdapter(
    private val items: List<Volume>,
    private val context: Context,
    private val onItemClick: (Volume) -> Unit
) : RecyclerView.Adapter<BookViewHolder>() {

    //Carregar arquivo de Layout e instaciar o BookViewHolder | Criar itens
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val layout = ItemBookBinding.inflate(LayoutInflater.from(context), parent, false)
        return BookViewHolder(layout)
    }

    // Exibir os itens que foram criados no "onCreatViewHolder"
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val volume = items[position]
        holder.txtTitle.text = volume.volumeInfo.title
        holder.txtAuthor.text = volume.volumeInfo.authors?.joinToString() ?: "-"
        holder.txtPages.text = volume.volumeInfo.pageCount?.toString() ?: "-"
        holder.itemView.setOnClickListener {
            onItemClick(volume)
        }

        if (volume.volumeInfo.imageLinks?.smallThumbnail != null) {
            Picasso.get().load(volume.volumeInfo.imageLinks?.smallThumbnail).into(holder.imageCover)
        } else {
            holder.imageCover.setImageResource(R.drawable.ic_baseline_broken_image_24)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}