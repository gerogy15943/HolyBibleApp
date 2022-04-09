package com.example.holybibleapp.presentation

import com.example.holybibleapp.domain.BookDomain
import com.example.holybibleapp.domain.BookDomainToUiMapper
import com.example.holybibleapp.domain.BooksDomainToBooksUiMapper
import com.example.holybibleapp.domain.ErrorType

class BaseBooksDomainToUiMapper(private val resourceProvider: ResourceProvider,
                                private val bookDomainToUiMapper: BookDomainToUiMapper): BooksDomainToBooksUiMapper {
    override fun map(list: List<BookDomain>): BooksUi {
        return BooksUi.Suceess(list,bookDomainToUiMapper)
    }

    override fun map(error: ErrorType): BooksUi {
        return BooksUi.Fail(error, resourceProvider)
    }
}