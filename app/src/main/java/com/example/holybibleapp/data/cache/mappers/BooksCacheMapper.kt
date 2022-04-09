package com.example.holybibleapp.data.cache.mappers

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.BookData
import com.example.holybibleapp.data.cache.room.BookDb

interface BooksCacheMapper: Abstract.Mapper {
    fun mapToData(list: List<BookDb>): List<BookData>
    fun mapToBookDb(list: List<BookData>): List<BookDb>

    class Base(val bookDbToDataMapper: BookDbToDataMapper,
               val bookDataToDbMapper: BookDataToDbMapper): BooksCacheMapper {
        override fun mapToData(list: List<BookDb>): List<BookData> {
            return list.map {
                it.map(bookDbToDataMapper)
            }
        }

        override fun mapToBookDb(list: List<BookData>): List<BookDb> {
            return list.map {
                it.mapTo(bookDataToDbMapper)
            }
        }


    }
}