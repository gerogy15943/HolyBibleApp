package com.example.holybibleapp.data.books

import com.example.holybibleapp.data.books.cache.BooksCacheDataSource
import com.example.holybibleapp.data.books.cache.mappers.BooksCacheMapper
import com.example.holybibleapp.data.books.net.BooksCloudDataSource
import com.example.holybibleapp.data.books.net.BooksCloudMapper
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
                var booksCacheList = booksCacheDataSource.fetchBooks()
                if(booksCacheList.isEmpty()){
                    val booksCloudList = booksCloudDataSource.getBooks()
                    val books = booksCloudMapper.map(booksCloudList)
                    booksCacheDataSource.saveBooks(books)
                }
                booksCacheList = booksCacheDataSource.fetchBooks()
                return BooksData.Success(booksCacheMapper.mapToData(booksCacheList))
            } catch (e: Exception){return BooksData.Fail(e)
            }
        }
    }
}