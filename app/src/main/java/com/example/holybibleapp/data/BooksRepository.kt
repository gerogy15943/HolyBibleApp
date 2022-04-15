package com.example.holybibleapp.data

import com.example.holybibleapp.data.cache.BooksCacheDataSource
import com.example.holybibleapp.data.cache.mappers.BooksCacheMapper
import com.example.holybibleapp.data.cache.room.BookDb
import com.example.holybibleapp.data.net.BooksCloudDataSource
import kotlinx.coroutines.delay
import java.lang.Exception

interface BooksRepository {
    suspend fun fetchBooks(): BooksData

    class Base(private val booksCloudDataSource: BooksCloudDataSource,
               private val booksCloudMapper: BooksCloudMapper,
               private val booksCacheDataSource: BooksCacheDataSource,
               private val booksCacheMapper: BooksCacheMapper
    ): BooksRepository {
        override suspend fun fetchBooks(): BooksData {
            try{
                val booksCloudList = booksCloudDataSource.getBooks()
                val books = booksCloudMapper.map(booksCloudList)
                booksCacheDataSource.saveBooks(books)
                val booksCacheList = booksCacheDataSource.fetchBooks()
                return BooksData.Success(booksCacheMapper.mapToData(booksCacheList))
            } catch (e: Exception){return BooksData.Fail(e)}
        }
    }
}