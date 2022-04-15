package com.example.holybibleapp.presentation

import com.example.holybibleapp.R
import com.example.holybibleapp.domain.BookDomain
import com.example.holybibleapp.domain.BookDomainToUiMapper
import com.example.holybibleapp.domain.BooksDomainToBooksUiMapper
import com.example.holybibleapp.domain.ErrorType

class BaseBooksDomainToUiMapper(private val resourceProvider: ResourceProvider,
                                private val mapper: BookDomainToUiMapper): BooksDomainToBooksUiMapper {
    override fun map(list: List<BookDomain>): BooksUi {
        val books =  list.map {
            it.map(mapper)
        }
        return BooksUi.Base(books)
    }

    override fun map(error: ErrorType): BooksUi {
        val messageId = when(error){
            ErrorType.NO_CONNNECTION -> R.string.no_connection_message
            ErrorType.SERVICE_UNAVALAIBLE -> R.string.service_unavalaible_message
            ErrorType.GENERIC_ERROR -> R.string.something_went_wrong
        }
        val message = resourceProvider.getStringRes(messageId)
        val listBookUi = listOf(BookUi.Fail(message))
        return BooksUi.Base(listBookUi)
    }
}