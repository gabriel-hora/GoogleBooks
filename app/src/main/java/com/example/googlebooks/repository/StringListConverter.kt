package com.example.googlebooks.repository

import androidx.room.TypeConverter

class StringListConverter {
    @TypeConverter
    fun stringToList(s: String?): List<String>? = s?.split(',')

    @TypeConverter
    fun listToString(list: List<String>?): String? = list?.joinToString()
}