package com.example.holybibleapp.domain

import com.example.holybibleapp.data.BookData
import com.example.holybibleapp.data.BookDataToDomainMapper
import com.example.holybibleapp.presentation.BookUi
import com.example.holybibleapp.presentation.BooksUi
import org.junit.Assert.*
import org.junit.Test

class BooksDomainTest {

    @Test
    fun test_map() {
        val domain = BooksDomain.Success(listOf(
            BookData("1", "Genesis", "ot"),
            BookData("66", "Revalation", "nt")
        ), object : BookDataToDomainMapper {
            override fun map(id: String, name: String, testament: String): BookDomain {
                return BookDomain.Base(id, name, testament)
            }
        })

        val actual = domain.map(object : BooksDomainToBooksUiMapper {
            override fun map(list: List<BookDomain>): BooksUi {
                return BooksUi.Success(list, object : BookDomainToUiMapper {
                    override fun map(id: String, name: String): BookUi {
                        return BookUi.Base(id, name)
                    }
                })
            }
            override fun map(error: ErrorType): BooksUi {
                throw IllegalAccessException()
            }
        })

        val expected = BooksUi.Success(listOf(
            BookDomain.Testament(TestamentType.OLD),
            BookDomain.Base("1", "Genesis", "ot"),
            BookDomain.Testament(TestamentType.NEW),
            BookDomain.Base("66", "Revalation", "nt")
        ), object : BookDomainToUiMapper {
            override fun map(id: String, name: String): BookUi {
                return BookUi.Base(id, name)
            }
        })

        assertEquals(expected, actual)
    }
}