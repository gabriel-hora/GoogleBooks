package com.example.googlebooks

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.googlebooks.repository.AppDatabase
import com.example.googlebooks.repository.Book
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val db = AppDatabase.getDatabase(appContext)
        val dao = db.getBookDao()

        runBlocking {
            val bookUnderTest = Book(
                "MEU_ID",
                "https://teste",
                "Meu Livro",
                "Descrição",
                listOf("Eu", "Ele"),
                "Gabriel Hora",
                "2022",
                1000,
                "http://small",
                "http://thumbnail"
            )
            val rowID = dao.save(bookUnderTest)
            assertTrue(rowID > -1)

            val books = dao.allFavorites().first()
            books.forEach {
                assertEquals(it.title, "Meu Livro")
            }

            val isFavorite = dao.isFavorite("MEU_ID")
            assertTrue(isFavorite == 1)

            val result = dao.delete(bookUnderTest)
            assertTrue(result == 1)
        }
    }
}