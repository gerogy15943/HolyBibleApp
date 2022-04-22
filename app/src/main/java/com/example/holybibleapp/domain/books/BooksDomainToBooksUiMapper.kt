package com.example.holybibleapp.domain.books

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.presentation.BooksUi

interface BooksDomainToBooksUiMapper: Abstract.Mapper {
    fun map(list: List<BookDomain>): BooksUi
    fun map(error: ErrorType): BooksUi
}