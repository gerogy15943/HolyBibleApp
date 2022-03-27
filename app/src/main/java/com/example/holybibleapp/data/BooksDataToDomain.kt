package com.example.holybibleapp.data

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.Book
import com.example.holybibleapp.data.net.BookServerModel
import com.example.holybibleapp.domain.BookDomain
import java.lang.Exception

interface BooksDataToDomain: Abstract.Mapper {
    fun map(list: List<Book>): BookDomain
    fun map(e: Exception): BookDomain

}
