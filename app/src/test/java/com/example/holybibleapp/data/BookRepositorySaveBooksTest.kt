package com.example.holybibleapp.data

import com.example.holybibleapp.core.Book
import com.example.holybibleapp.data.cache.mappers.BookDbToBookMapper
import com.example.holybibleapp.data.cache.mappers.BookToBookDbMapper
import com.example.holybibleapp.data.cache.mappers.BooksCacheDataSource
import com.example.holybibleapp.data.cache.mappers.BooksCacheMapper
import com.example.holybibleapp.data.cache.room.BookDb
import com.example.holybibleapp.data.net.BookServerModel
import com.example.holybibleapp.data.net.BookServerToBookMapper
import com.example.holybibleapp.data.net.BooksCloudDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.io.IOException
import java.net.UnknownHostException

class BookRepositorySaveBooksTest: BaseBooksRepositoryTest(){

    @Test
    fun test_save_books() = runBlocking {
        val testCloudDataSource = TestCloudDataSource()
        val testCacheDataSource = TestCacheDataSource(BookToBookDbMapper.Base())
        val repository = BooksRepository.Base(
            testCloudDataSource,
            TestBooksCloudMapper(BookServerToBookMapper.Base()),
            testCacheDataSource,
            TestBooksCacheMapper(BookDbToBookMapper.Base(), BookToBookDbMapper.Base())
        )

        val actualCloud = repository.fetchBooks()
        val expectedCloud = BooksData.Success(listOf(
            Book("0", "name0"),
            Book("1", "name1"),
            Book("2", "name2"),
        ))

        Assert.assertEquals(expectedCloud, actualCloud)

        val actualCache = repository.fetchBooks()
        val expectedCache = BooksData.Success(listOf(
            Book("0", "name0db"),
            Book("1", "name1db"),
            Book("2", "name2db"),
        ))

        Assert.assertEquals(expectedCache, actualCache)
    }

}