package com.example.holybibleapp.data.net

interface BooksCloudDataSource {

    suspend fun getBooks(): List<BookServerModel>

    class Base(private val service: BookService): BooksCloudDataSource{
        override suspend fun getBooks(): List<BookServerModel> {
            return service.getBooks()
        }

    }
}