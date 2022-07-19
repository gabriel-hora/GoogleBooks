package com.example.googlebooks.repository

import android.content.Context
import com.example.googlebooks.model.Volume
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookRepository(context: Context) {

    private val dao: BookDao = AppDatabase.getDatabase(context).getBookDao()

    suspend fun save(volume: Volume) {
        dao.save(BookVolumeMapper.volumeToBook(volume))
    }

    suspend fun delete(volume: Volume) {
        dao.delete(BookVolumeMapper.volumeToBook(volume))
    }

    suspend fun isFavorite(id: String): Boolean {
        return dao.isFavorite(id) > 0
    }

    fun allFavorites(): Flow<List<Volume>> {
        return dao.allFavorites()
            .map { bookList ->
                bookList.map { book ->
                    BookVolumeMapper.bookToVolume(book)
                }
            }
    }
}