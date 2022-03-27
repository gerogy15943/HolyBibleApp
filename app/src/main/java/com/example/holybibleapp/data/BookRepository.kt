package com.example.holybibleapp.data

import com.example.holybibleapp.data.cache.mappers.BooksCacheDataSource
import com.example.holybibleapp.data.cache.mappers.BooksCacheMapper
import com.example.holybibleapp.data.net.BooksCloudDataSource
import java.lang.Exception

interface BookRepository {
    suspend fun fetchBooks(): BooksData

    class Base(private val booksCloudDataSource: BooksCloudDataSource,
               private val booksCloudMapper: BooksCloudMapper,
               private val booksCacheDataSource: BooksCacheDataSource,
               private val booksCacheMapper: BooksCacheMapper
    ): BookRepository {
        override suspend fun fetchBooks(): BooksData {
            try{
                val booksCacheList = booksCacheDataSource.fetchBooks()
                if(booksCacheList.isEmpty()){
                    val booksCloudList = booksCloudDataSource.getBooks()
                    val books = booksCloudMapper.map(booksCloudList)
                    booksCacheDataSource.saveBooks(books)
                    return BooksData.Success(books)
                }
                else{
                    return BooksData.Success(booksCacheMapper.mapToBook(booksCacheList))
                }
            } catch (e: Exception){return BooksData.Fail(e)}
        }
    }
}