package com.example.holybibleapp.data

import com.example.holybibleapp.data.books.BookData
import com.example.holybibleapp.data.books.BooksData
import com.example.holybibleapp.data.books.BooksRepository
import com.example.holybibleapp.data.books.cache.mappers.BookDataToDbMapper
import com.example.holybibleapp.data.books.cache.mappers.BookDbToDataMapper
import com.example.holybibleapp.data.books.cache.mappers.BooksCacheMapper
import com.example.holybibleapp.data.books.net.BookServerToDataMapper
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class BookRepositorySaveBooksTest: BaseBooksRepositoryTest(){

    @Test
    fun test_save_books() = runBlocking {
        val testCloudDataSource = TestCloudDataSource()
        val bookDbToDataMapper = BookDbToDataMapper.Base()
        val bookDataToDbMapper = BookDataToDbMapper.Base()
        val testBooksCacheMapper = BooksCacheMapper.Base(bookDbToDataMapper, bookDataToDbMapper)
        val testCacheDataSource = TestCacheDataSource(BooksCacheMapper.Base(bookDbToDataMapper, bookDataToDbMapper))
        val repository = BooksRepository.Base(
            testCloudDataSource,
            TestBooksCloudMapper(BookServerToDataMapper.Base()),
            testCacheDataSource,
            testBooksCacheMapper)
        testCacheDataSource.saveBooks(listOf(
            BookData("0", "name0", "ot"),
            BookData("1", "name1", "ot"),
            BookData("2", "name2", "ot")
        ))

        val actualCloud = repository.fetchBooks()
        val expectedCloud = BooksData.Success(listOf(
            BookData("0", "name0", "ot"),
            BookData("1", "name1", "ot"),
            BookData("2", "name2", "ot")
        ))

        Assert.assertEquals(expectedCloud, actualCloud)

    }

}