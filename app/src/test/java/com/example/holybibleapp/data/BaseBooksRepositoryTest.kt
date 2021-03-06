package com.example.holybibleapp.data

import com.example.holybibleapp.data.cache.mappers.BookDbToDataMapper
import com.example.holybibleapp.data.cache.BooksCacheDataSource
import com.example.holybibleapp.data.cache.mappers.BookDataToDbMapper
import com.example.holybibleapp.data.cache.mappers.BooksCacheMapper
import com.example.holybibleapp.data.cache.room.BookDb
import com.example.holybibleapp.data.net.BookServerModel
import com.example.holybibleapp.data.net.BookServerToDataMapper
import com.example.holybibleapp.data.net.BooksCloudDataSource

abstract class BaseBooksRepositoryTest {

    protected inner class TestCacheDataSource(private val mapper: BooksCacheMapper) :
        BooksCacheDataSource {

        private val listDb = ArrayList<BookDb>()

        override fun fetchBooks(): List<BookDb> {
            return listDb
        }

        override fun saveBooks(list: List<BookData>) {
            val result = mapper.mapToBookDb(list)
            listDb.clear()
            listDb.addAll(result)
        }

    }


    protected inner class TestBooksCloudMapper(private val bookServerToDataMapper: BookServerToDataMapper): BooksCloudMapper{
        override fun map(cloudList: List<BookServerModel>): List<BookData> {
            return cloudList.map {
                it.map(bookServerToDataMapper)
            }
        }
    }

   protected inner class TestBooksCacheMapper(val bookDbToDataMapper: BookDbToDataMapper,
                                              val bookDataToDbMapper: BookDataToDbMapper
    ): BooksCacheMapper {
        override fun mapToData(list: List<BookDb>): List<BookData> {
            return list.map {
                it.map(bookDbToDataMapper)
            }
        }

        override fun mapToBookDb(list: List<BookData>): List<BookDb> {
            return list.map {
                it.mapTo(bookDataToDbMapper)
            }
        }


    }

    protected inner class TestCloudDataSource(
    ) : BooksCloudDataSource {
        override suspend fun getBooks(): List<BookServerModel> {
            return listOf(
                BookServerModel("0", "name0", "ot"),
                BookServerModel("1", "name1", "ot"),
                BookServerModel("2", "name2", "ot"),
            )
        }

    }
}