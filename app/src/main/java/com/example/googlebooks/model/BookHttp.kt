package com.example.googlebooks.model

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception
import java.util.concurrent.TimeUnit

object BookHttp {

    private const val API_KEY = "AIzaSyDOjExGxATmzZmVlOqqxi8am9f33R63FFU"
    private const val BOOK_JSON_URL = "https://www.googleapis.com/books/v1/volumes?q=%s&key=$API_KEY"

    //Contruir a comunicação com a API | instanciar o Client
    private val client = OkHttpClient.Builder()
            //quanto tempo demora para o servidor responder após conectar
        .readTimeout(10, TimeUnit.SECONDS)

            //quanto tempo demora para conectar com o servidor
        .connectTimeout(10,TimeUnit.SECONDS)
        .build()

    //Requisição
    fun searchBook(q: String): SearchResult? {
        val request = Request.Builder()
            .url(String.format(BOOK_JSON_URL, q))
            .build()

        try {
            val response = client.newCall(request).execute()
            val json = response.body?.string()
            return Gson().fromJson(json, SearchResult::class.java)

        } catch (e: Exception){
            e.printStackTrace()
        }
        return null
    }
}