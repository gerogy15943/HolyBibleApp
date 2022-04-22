package com.example.holybibleapp.domain.books

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.presentation.BooksUi

sealed class BooksDomain: Abstract.Object<BooksUi, BooksDomainToBooksUiMapper>{
    data class Success(private val list: List<BookDomain>): BooksDomain() {
        override fun map(mapper: BooksDomainToBooksUiMapper): BooksUi {
            return mapper.map(list)
        }
    }

    data class Fail(private val errorType: ErrorType): BooksDomain(){
        override fun map(mapper: BooksDomainToBooksUiMapper): BooksUi {
            return mapper.map(errorType)
        }

    }
}
