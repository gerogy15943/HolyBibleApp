package com.example.holybibleapp.data

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.net.BookServerModel
import com.example.holybibleapp.data.net.BookServerToDataMapper

interface BooksCloudMapper: Abstract.Mapper {
    fun map(cloudList: List<BookServerModel>): List<BookData>

    class Base(private val bookServerToDataMapper: BookServerToDataMapper): BooksCloudMapper{
        override fun map(cloudList: List<BookServerModel>): List<BookData> {
            return cloudList.map {
                it.map(bookServerToDataMapper)
            }
        }
    }
}
