package com.example.holybibleapp.data.books.net

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.books.BookData

interface BooksCloudMapper: Abstract.Mapper {
    fun map(cloudList: List<BookServerModel>): List<BookData>

    class Base(private val bookServerToDataMapper: BookServerToDataMapper): BooksCloudMapper {
        override fun map(cloudList: List<BookServerModel>): List<BookData> {
            return cloudList.map {
                it.map(bookServerToDataMapper)
            }
        }
    }
}
