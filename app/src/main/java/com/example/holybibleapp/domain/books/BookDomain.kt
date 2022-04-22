package com.example.holybibleapp.domain.books

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.presentation.BookUi

sealed class BookDomain(): Abstract.Object<BookUi, BookDomainToUiMapper> {

    data class Base(private val id: String, private val name: String, private val testament: String): BookDomain(){
        override fun map(mapper: BookDomainToUiMapper): BookUi {
            return mapper.map(id, name)
        }
    }

    data class Testament(private val type: TestamentType): BookDomain(){
        override fun map(mapper: BookDomainToUiMapper): BookUi {
           return type.map(mapper)
        }
    }

}

