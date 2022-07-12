package com.example.googlebooks.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googlebooks.model.Volume
import com.example.googlebooks.repository.BookRepository
import kotlinx.coroutines.launch

class BookDetailViewModel(
    private val repository: BookRepository
): ViewModel() {
    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    fun onCreate(volume: Volume) {
        viewModelScope.launch {
            _isFavorite.value = repository.isFavorite(volume.id)
        }
    }

    fun saveToFavorites(volume: Volume) {
        viewModelScope.launch {
            repository.save(volume)
            _isFavorite.value = repository.isFavorite(volume.id)
        }
    }

    fun removeFromFavorite(volume: Volume){
        viewModelScope.launch {
            repository.delete(volume)
            _isFavorite.value = repository.isFavorite(volume.id)
        }
    }
}