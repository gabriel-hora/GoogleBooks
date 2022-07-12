package com.example.googlebooks.repository

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(book: Book): Long

    @Delete
    suspend fun delete(vararg book: Book): Int

    @Query("SELECT * FROM Book")
    fun allFavorites(): Flow<List<Book>>

    @Query("SELECT COUNT(id) FROM Book WHERE id = :id")
    suspend fun isFavorite(id: String): Int
}