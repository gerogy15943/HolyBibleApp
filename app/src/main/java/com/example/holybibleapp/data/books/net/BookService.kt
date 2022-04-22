package com.example.holybibleapp.data.books.net

import retrofit2.http.GET

interface BookService {

    @GET("books")
    suspend fun getBooks(): List<BookServerModel>
}