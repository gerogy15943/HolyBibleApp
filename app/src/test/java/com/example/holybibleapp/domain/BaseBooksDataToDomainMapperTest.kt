package com.example.holybibleapp.domain

import com.example.holybibleapp.data.books.BookData
import com.example.holybibleapp.data.books.BookDataToDomainMapper
import com.example.holybibleapp.data.books.BooksData
import com.example.holybibleapp.domain.books.*
import org.junit.Assert.*
import org.junit.Test
import java.lang.IllegalStateException
import java.net.UnknownHostException

class BaseBooksDataToDomainMapperTest {

    @Test
    fun test_map() {
        val domain = BooksData.Success(listOf(
            BookData("1", "Genesis", "OT"),
            BookData("66", "Revalation", "NT")
        ))

        val actual = domain.map(BaseBooksDataToDomainMapper(object: BookDataToDomainMapper {
            override fun map(id: String, name: String, testament: String): BookDomain {
                return BookDomain.Base(id, name, testament)
            }
        }))

        val expected = BooksDomain.Success(listOf(
            BookDomain.Testament(TestamentType.OLD),
            BookDomain.Base("1", "Genesis", "OT"),
            BookDomain.Testament(TestamentType.NEW),
            BookDomain.Base("66", "Revalation", "NT")
        ))

        assertEquals(expected, actual)
    }

    @Test
    fun test_fail(){
        val mapper = BaseBooksDataToDomainMapper(object: BookDataToDomainMapper {
            override fun map(id: String, name: String, testament: String): BookDomain {
                return BookDomain.Base(id, name, testament)
            }
        })

        var actual = mapper.map(UnknownHostException())
        var expected = BooksDomain.Fail(ErrorType.NO_CONNNECTION)
        assertEquals(expected, actual)
        actual = mapper.map(IllegalStateException())
        expected = BooksDomain.Fail(ErrorType.GENERIC_ERROR)
        assertEquals(expected, actual)

    }
}