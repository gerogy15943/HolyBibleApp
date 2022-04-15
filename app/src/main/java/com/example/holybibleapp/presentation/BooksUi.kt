package com.example.holybibleapp.presentation

import com.example.holybibleapp.R
import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.domain.BookDomain
import com.example.holybibleapp.domain.BookDomainToUiMapper
import com.example.holybibleapp.domain.ErrorType

sealed class BooksUi: Abstract.Object<Unit, BooksCommunication>{

    data class Base(private val list: List<BookUi> ): BooksUi() {
        override fun map(mapper: BooksCommunication) {
            mapper.map(list)
        }

    }
}
