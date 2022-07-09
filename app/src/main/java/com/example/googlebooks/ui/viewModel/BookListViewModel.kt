package com.example.googlebooks.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googlebooks.model.BookHttp
import com.example.googlebooks.model.Volume
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookListViewModel: ViewModel() {

    private val _booksList = MutableLiveData<List<Volume>?>()
    val booksList : MutableLiveData<List<Volume>?>
        get() = _booksList

    fun loadBooks() {
        if (_booksList.value != null) return

        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                BookHttp.searchBook("Dominando o Android")
            }
            _booksList.value = result?.items
        }
    }
}