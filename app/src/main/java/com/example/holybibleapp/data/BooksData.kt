package com.example.holybibleapp.data

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.Book
import com.example.holybibleapp.data.net.BookServerModel
import com.example.holybibleapp.domain.BookDomain
import java.lang.Exception

sealed class BooksData: Abstract.Object<BookDomain, BooksDataToDomain>(){
    class Success(private val bookListModel: List<Book>): BooksData() {
        override fun map(mapper: BooksDataToDomain): BookDomain {
            return mapper.map(bookListModel)
        }
    }

    class Fail(e: Exception): BooksData() {
        override fun map(mapper: BooksDataToDomain): BookDomain {
            TODO("Not yet implemented")
        }
    }
}
