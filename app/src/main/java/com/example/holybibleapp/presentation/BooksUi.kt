package com.example.holybibleapp.presentation

import com.example.holybibleapp.R
import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.Book
import com.example.holybibleapp.domain.ErrorType

sealed class BooksUi: Abstract.Object<Unit, Abstract.Mapper.Empty>(){
    class Suceess(private val list: List<Book>,
    private val communication: BooksCommunication): BooksUi() {
        override fun map(mapper: Abstract.Mapper.Empty) {
            communication.show(list)
        }
    }

    class Fail(private val errorType: ErrorType,
               private val communication: BooksCommunication,
                private val resourceProvider: ResourceProvider): BooksUi() {
        override fun map(mapper: Abstract.Mapper.Empty) {
            val messageId = when(errorType){
                ErrorType.NO_CONNNECTION -> R.string.no_connection_message
                ErrorType.SERVICE_UNAVALAIBLE -> R.string.service_unavalaible_message
                ErrorType.GENERIC_ERROR -> R.string.something_went_wrong
            }
            communication.show(resourceProvider.getStringRes(messageId))
        }

    }
}
