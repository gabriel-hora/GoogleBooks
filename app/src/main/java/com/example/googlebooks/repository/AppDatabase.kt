package com.example.googlebooks.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Book::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase (): RoomDatabase() {

    abstract fun getBookDao(): BookDao

    companion object {
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "appDb"
                ).build()
            }
            return instance!!
        }
    }
}