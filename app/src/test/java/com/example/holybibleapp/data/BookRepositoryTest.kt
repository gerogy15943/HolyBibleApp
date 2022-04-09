package com.example.holybibleapp.data

import com.example.holybibleapp.core.Book
import com.example.holybibleapp.data.cache.mappers.BookDbToDataMapper
import com.example.holybibleapp.data.cache.mappers.BookToBookDbMapper
import com.example.holybibleapp.data.cache.BooksCacheDataSource
import com.example.holybibleapp.data.cache.mappers.BooksCacheMapper
import com.example.holybibleapp.data.cache.room.BookDb
import com.example.holybibleapp.data.net.BookServerModel
import com.example.holybibleapp.data.net.BookServerToDataMapper
import com.example.holybibleapp.data.net.BooksCloudDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import java.io.IOException
import java.net.UnknownHostException

class BookRepositoryTest: BaseBooksRepositoryTest() {

    private val exception = UnknownHostException()

    @Test
    fun test_no_connection_no_cache() = runBlocking {
        val testCloudDataSource = TestCloudDataSource(false, true)
        val testCacheDataSource = TestCacheDataSource(false)
        val repository = BooksRepository.Base(
            testCloudDataSource,
            BooksCloudMapper.Base(BookServerToDataMapper.Base()),
            testCacheDataSource,
            BooksCacheMapper.Base(BookDbToDataMapper.Base(), BookToBookDbMapper.Base())
        )

        val actual = repository.fetchBooks()
        val expected = BooksData.Fail(exception)

        assertEquals(expected, actual)
    }

    @Test
    fun test_cloud_success_no_cache() = runBlocking{
        val testCloudDataSource = TestCloudDataSource(true, true)
        val testCacheDataSource = TestCacheDataSource(false)
        val repository = BooksRepository.Base(
            testCloudDataSource,
            BooksCloudMapper.Base(BookServerToDataMapper.Base()),
            testCacheDataSource,
            BooksCacheMapper.Base(BookDbToDataMapper.Base(), BookToBookDbMapper.Base())
        )

        val actual = repository.fetchBooks()
        val expected = BooksData.Success(listOf(Book("0", "name")))
    }

    @Test
    fun test_no_connection_with_cache() = runBlocking {
        val testCloudDataSource = TestCloudDataSource(false, true)
        val testCacheDataSource = TestCacheDataSource(true)
        val repository = BooksRepository.Base(
            testCloudDataSource,
            BooksCloudMapper.Base(BookServerToDataMapper.Base()),
            testCacheDataSource,
            BooksCacheMapper.Base(BookDbToDataMapper.Base(), BookToBookDbMapper.Base())
        )

        val actual = repository.fetchBooks()
        val expected = BooksData.Success(listOf(Book("0", "name")))
    }

    @Test
    fun test_cloud_success_with_cache() = runBlocking{
        val testCloudDataSource = TestCloudDataSource(true, true)
        val testCacheDataSource = TestCacheDataSource(true)
        val repository = BooksRepository.Base(
            testCloudDataSource,
            TestBooksCloudMapper(BookServerToDataMapper.Base()),
            testCacheDataSource,
            TestBooksCacheMapper(BookDbToDataMapper.Base(), BookToBookDbMapper.Base())
        )

        val actual = repository.fetchBooks()
        val expected = BooksData.Success(listOf(
            Book("10", "name10"),
            Book("11", "name11"),
            Book("12", "name12")
        ))

        assertEquals(expected, actual)
    }
    inner class TestCacheDataSource(private val returnSuccess: Boolean) : BooksCacheDataSource {
        override fun fetchBooks(): List<BookDb> {
            if (returnSuccess) {
                return listOf(
                    BookDb("10", "name10"),
                    BookDb("11", "name11"),
                    BookDb("12", "name12")
                )
            } else
                return emptyList()
        }

        override fun saveBooks(list: List<Book>) {
            //todo
        }

    }

    inner class TestCloudDataSource(
        private val returnSuccess: Boolean,
        private val errorTypeNoConnection: Boolean
    ) : BooksCloudDataSource {
        override suspend fun getBooks(): List<BookServerModel> {
            if (returnSuccess)
                return listOf(
                    BookServerModel("0", "name0"),
                    BookServerModel("1", "name1"),
                    BookServerModel("2", "name2"),
                )
            else {
                if (errorTypeNoConnection)
                    throw exception
                else
                    throw IOException()
            }
        }

    }
}