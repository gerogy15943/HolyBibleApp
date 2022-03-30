package com.example.holybibleapp.domain

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.Book
import com.example.holybibleapp.presentation.BooksUi

interface BooksDomainToBooksUiMapper: Abstract.Mapper {
    fun map(list: List<Book>): BooksUi
    fun map(error: ErrorType): BooksUi
}