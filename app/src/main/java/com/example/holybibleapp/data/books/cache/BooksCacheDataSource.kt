package com.example.holybibleapp.data.books.cache

import com.example.holybibleapp.data.books.BookData
import com.example.holybibleapp.data.books.cache.mappers.BooksCacheMapper
import com.example.holybibleapp.data.books.cache.room.BookDb
import com.example.holybibleapp.core.RoomProvider

interface BooksCacheDataSource {
    fun fetchBooks(): List<BookDb>

    fun saveBooks(list: List<BookData>)

    class Base(private val roomProvider: RoomProvider,
               private val booksCacheMapper: BooksCacheMapper
    ): BooksCacheDataSource {
        override fun fetchBooks(): List<BookDb> {
            val bookDao = roomProvider.provide()?.BookDao()
            return bookDao?.fetchBooks() ?: emptyList()
        }

        override fun saveBooks(list: List<BookData>) {
            val bookDao = roomProvider.provide()?.BookDao()
            bookDao?.insert(booksCacheMapper.mapToDb(list))
        }
    }
}