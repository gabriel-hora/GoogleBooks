package com.example.googlebooks.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googlebooks.model.BookHttp
import com.example.googlebooks.model.Volume
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class BookListViewModel: ViewModel() {

    private val _state = MutableLiveData<State>()
    val state : LiveData<State>
        get() = _state

    fun loadBooks() {
        if (_state.value is State.Loaded) return

        search("Dominando Android")
    }

    fun search(query: String) {
        viewModelScope.launch {
            _state.value = State.Loading
            delay(2000)
            val result = withContext(Dispatchers.IO){
                BookHttp.searchBook(query)
            }
            if (result?.items == null) {
                _state.value = State.Error(Exception("Error loading Books"), false)
            } else {
                _state.value = State.Loaded(result.items)
            }
        }
    }

    sealed class State {
        object Loading: State()
        data class Loaded(val items: List<Volume>): State()
        data class Error(val e: Throwable, var hasConsumed: Boolean): State()
    }
}