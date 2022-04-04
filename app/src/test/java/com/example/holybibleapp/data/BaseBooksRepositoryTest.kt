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

abstract class BaseBooksRepositoryTest {

    protected inner class TestCacheDataSource(private val mapper: BookToBookDbMapper) : BooksCacheDataSource {

        private val listDb = ArrayList<BookDb>()

        override fun fetchBooks(): List<BookDb> {
            return listDb
        }

        override fun saveBooks(list: List<Book>) {
            val result = list.map {
                mapper.map(it.id, it.name + "db")
            }
            listDb.clear()
            listDb.addAll(result)
        }

    }


    protected inner class TestBooksCloudMapper(private val bookServerToBookMapper: BookServerToBookMapper): BooksCloudMapper{
        override fun map(cloudList: List<BookServerModel>): List<Book> {
            return cloudList.map {
                it.map(bookServerToBookMapper)
            }
        }
    }

   protected inner class TestBooksCacheMapper(val bookDbToBookMapper: BookDbToBookMapper,
                                     val bookToBookDbMapper: BookToBookDbMapper
    ): BooksCacheMapper {
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

    protected inner class TestCloudDataSource(
    ) : BooksCloudDataSource {
        override suspend fun getBooks(): List<BookServerModel> {
            return listOf(
                BookServerModel("0", "name0"),
                BookServerModel("1", "name1"),
                BookServerModel("2", "name2"),
            )
        }

    }
}