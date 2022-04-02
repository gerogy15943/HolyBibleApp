package com.example.holybibleapp.presentation

import com.example.holybibleapp.core.Book
import com.example.holybibleapp.domain.BooksDomainToBooksUiMapper
import com.example.holybibleapp.domain.ErrorType

class BaseBooksDomainMapper(private val communication: BooksCommunication,
                            private val resourceProvider: ResourceProvider): BooksDomainToBooksUiMapper {
    override fun map(list: List<Book>): BooksUi {
        return BooksUi.Suceess(list, communication)
    }

    override fun map(error: ErrorType): BooksUi {
        return BooksUi.Fail(error, communication, resourceProvider)
    }
}