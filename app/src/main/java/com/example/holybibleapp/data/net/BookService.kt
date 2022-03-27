package com.example.holybibleapp.data.net

import retrofit2.http.GET

interface BookService {

    @GET("books")
    suspend fun getBooks(): List<BookServerModel>
}