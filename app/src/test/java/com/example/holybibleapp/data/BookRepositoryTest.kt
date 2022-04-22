package com.example.holybibleapp.data
import com.example.holybibleapp.data.books.BookData
import com.example.holybibleapp.data.books.net.BooksCloudMapper
import com.example.holybibleapp.data.books.BooksData
import com.example.holybibleapp.data.books.BooksRepository
import com.example.holybibleapp.data.books.cache.mappers.BookDbToDataMapper
import com.example.holybibleapp.data.books.cache.BooksCacheDataSource
import com.example.holybibleapp.data.books.cache.mappers.BookDataToDbMapper
import com.example.holybibleapp.data.books.cache.mappers.BooksCacheMapper
import com.example.holybibleapp.data.books.cache.room.BookDb
import com.example.holybibleapp.data.books.net.BookServerModel
import com.example.holybibleapp.data.books.net.BookServerToDataMapper
import com.example.holybibleapp.data.books.net.BooksCloudDataSource
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
            BooksCacheMapper.Base(BookDbToDataMapper.Base(), BookDataToDbMapper.Base())
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
            BooksCacheMapper.Base(BookDbToDataMapper.Base(), BookDataToDbMapper.Base())
        )

        val actual = repository.fetchBooks()
        val expected = BooksData.Success(listOf(BookData("0", "name", "ot")))

        assertEquals(expected, actual)
    }

    @Test
    fun test_no_connection_with_cache() = runBlocking {
        val testCloudDataSource = TestCloudDataSource(false, true)
        val testCacheDataSource = TestCacheDataSource(true)
        val repository = BooksRepository.Base(
            testCloudDataSource,
            BooksCloudMapper.Base(BookServerToDataMapper.Base()),
            testCacheDataSource,
            BooksCacheMapper.Base(BookDbToDataMapper.Base(), BookDataToDbMapper.Base())
        )

        val actual = repository.fetchBooks()
        val expected = BooksData.Success(listOf(BookData("0", "name","ot")))

        assertEquals(expected, actual)
    }

    @Test
    fun test_cloud_success_with_cache() = runBlocking{
        val testCloudDataSource = TestCloudDataSource(true, true)
        val testCacheDataSource = TestCacheDataSource(true)
        val repository = BooksRepository.Base(
            testCloudDataSource,
            TestBooksCloudMapper(BookServerToDataMapper.Base()),
            testCacheDataSource,
            TestBooksCacheMapper(BookDbToDataMapper.Base(), BookDataToDbMapper.Base())
        )

        val actual = repository.fetchBooks()
        val expected = BooksData.Success(listOf(
            BookData("10", "name10","ot"),
            BookData("11", "name11", "ot"),
            BookData("12", "name12", "ot")
        ))

        assertEquals(expected, actual)
    }
    inner class TestCacheDataSource(private val returnSuccess: Boolean) : BooksCacheDataSource {
        private val listDb = mutableListOf<BookDb>()
        override fun fetchBooks(): List<BookDb> {
            if (returnSuccess) {
                return listDb
            } else
                return emptyList()
        }

        override fun saveBooks(list: List<BookData>) {
            listDb.add(BookDb("10", "name10", "ot"))
            listDb.add(BookDb("11", "name11", "ot"))
            listDb.add(BookDb("12", "name12", "ot"))
        }

    }

    inner class TestCloudDataSource(
        private val returnSuccess: Boolean,
        private val errorTypeNoConnection: Boolean
    ) : BooksCloudDataSource {
        override suspend fun getBooks(): List<BookServerModel> {
            if (returnSuccess)
                return listOf(
                    BookServerModel("0", "name0", "ot"),
                    BookServerModel("1", "name1", "ot"),
                    BookServerModel("2", "name2", "ot"),
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