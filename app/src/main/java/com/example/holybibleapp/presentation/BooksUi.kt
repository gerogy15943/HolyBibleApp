package com.example.holybibleapp.presentation

import com.example.holybibleapp.R
import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.domain.BookDomain
import com.example.holybibleapp.domain.BookDomainToUiMapper
import com.example.holybibleapp.domain.ErrorType

sealed class BooksUi: Abstract.Object<Unit, BooksCommunication>{
    data class Success(private val list: List<BookDomain>,
                       private val bookDomainToUiMapper: BookDomainToUiMapper ): BooksUi() {
        override fun map(mapper: BooksCommunication) {
            val listBookUi = list.map {
                it.map(bookDomainToUiMapper)
            }
            mapper.map(listBookUi)
        }

    }

    data class Fail(private val errorType: ErrorType,
                private val resourceProvider: ResourceProvider): BooksUi() {
        override fun map(mapper: BooksCommunication) {
            val messageId = when(errorType){
                ErrorType.NO_CONNNECTION -> R.string.no_connection_message
                ErrorType.SERVICE_UNAVALAIBLE -> R.string.service_unavalaible_message
                ErrorType.GENERIC_ERROR -> R.string.something_went_wrong
            }
            val message = resourceProvider.getStringRes(messageId)
            val listBookUi = listOf(BookUi.Fail(message))
            mapper.map(listBookUi)
        }

    }
}
