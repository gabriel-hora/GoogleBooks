package com.example.googlebooks.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.googlebooks.repository.BookRepository

class BookFavoritesViewModel(repository: BookRepository) : ViewModel() {

    val favoriteBooks = repository.allFavorites().asLiveData()

}