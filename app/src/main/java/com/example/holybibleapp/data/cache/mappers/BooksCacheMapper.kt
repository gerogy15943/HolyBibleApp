package com.example.holybibleapp.data.cache.mappers

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.Book
import com.example.holybibleapp.data.cache.room.BookDb

interface BooksCacheMapper: Abstract.Mapper {
    fun mapToBook(list: List<BookDb>): List<Book>
    fun mapToBookDb(list: List<Book>): List<BookDb>

    class Base(val bookDbToBookMapper: BookDbToBookMapper,
            val bookToBookDbMapper: BookToBookDbMapper): BooksCacheMapper {
        override fun mapToBook(list: List<BookDb>): List<Book> {
            return list.map {
                it.map(bookDbToBookMapper)
            }
        }

        override fun mapToBookDb(list: List<Book>): List<BookDb> {
            return list.map {
                bookToBookDbMapper.map(it.id, it.name)
            }
        }


    }
}