package com.example.holybibleapp.data.books

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.domain.books.BooksDomain
import java.lang.Exception

interface BooksDataToDomainMapper: Abstract.Mapper {
    fun map(list: List<BookData>): BooksDomain
    fun map(e: Exception): BooksDomain

}
