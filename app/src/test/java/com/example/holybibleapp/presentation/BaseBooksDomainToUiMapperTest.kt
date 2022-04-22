package com.example.holybibleapp.presentation

import com.example.holybibleapp.R
import com.example.holybibleapp.domain.books.BookDomainToUiMapper
import com.example.holybibleapp.domain.books.ErrorType
import org.junit.Assert.*
import org.junit.Test
import java.lang.IllegalStateException

class BaseBooksDomainToUiMapperTest {

    @Test
    fun test_fail() {
        val resourceProvider = TestResourceProvider()
        val mapper = BaseBooksDomainToUiMapper(resourceProvider, object: BookDomainToUiMapper {
            override fun map(id: String, name: String): BookUi {
                throw IllegalStateException()
            }

        })
        var actual = (mapper.map(ErrorType.NO_CONNNECTION))
        var expected = BooksUi.Base(listOf(BookUi.Fail("no_connection")))
        assertEquals(expected, actual)

        actual = (mapper.map(ErrorType.SERVICE_UNAVALAIBLE))
        expected = BooksUi.Base(listOf(BookUi.Fail("service_unavalable")))
        assertEquals(expected, actual)

        actual = (mapper.map(ErrorType.GENERIC_ERROR))
        expected = BooksUi.Base(listOf(BookUi.Fail("something_went_wrong")))
        assertEquals(expected, actual)
    }

    private inner class TestResourceProvider: ResourceProvider {
        override fun getStringRes(id: Int): String {
            return when (id){
                R.string.no_connection_message -> "no_connection"
                R.string.service_unavalaible_message -> "service_unavalable"
                else -> "something_went_wrong"
            }
        }

    }
}
