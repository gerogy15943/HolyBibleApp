package com.example.holybibleapp.data.books

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.domain.books.BooksDomain
import java.lang.Exception

sealed class BooksData: Abstract.Object<BooksDomain, BooksDataToDomainMapper> {
    data class Success(private val bookList: List<BookData>): BooksData() {
        override fun map(mapper: BooksDataToDomainMapper): BooksDomain {
            return mapper.map(bookList)
        }
    }

    data class Fail(private val e: Exception): BooksData() {
        override fun map(mapper: BooksDataToDomainMapper): BooksDomain {
            return mapper.map(e)
        }
    }
}
