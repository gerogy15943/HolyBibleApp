package com.example.holybibleapp.data

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.Book
import com.example.holybibleapp.data.net.BookServerModel
import com.example.holybibleapp.data.net.BookServerToBookMapper

interface BooksCloudMapper: Abstract.Mapper {
    fun map(cloudList: List<BookServerModel>): List<Book>

    class   Base(private val bookServerToBookMapper: BookServerToBookMapper): BooksCloudMapper{
        override fun map(cloudList: List<BookServerModel>): List<Book> {
            return cloudList.map {
                it.map(bookServerToBookMapper)
            }
        }

    }
}
