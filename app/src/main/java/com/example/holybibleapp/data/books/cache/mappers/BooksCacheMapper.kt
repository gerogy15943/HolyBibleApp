package com.example.holybibleapp.data.books.cache.mappers

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.books.BookData
import com.example.holybibleapp.data.books.cache.room.BookDb

interface BooksCacheMapper: Abstract.Mapper {
    fun mapToData(list: List<BookDb>): List<BookData>
    fun mapToDb(list: List<BookData>): List<BookDb>

    class Base(private val bookDbToDataMapper: BookDbToDataMapper,
               private val bookDataToDbMapper: BookDataToDbMapper
    ): BooksCacheMapper {
        override fun mapToData(list: List<BookDb>): List<BookData> {
            return list.map {
                it.map(bookDbToDataMapper)
            }
        }

        override fun mapToDb(list: List<BookData>): List<BookDb> {
            return list.map {
                it.mapToDb(bookDataToDbMapper)
            }
        }


    }
}