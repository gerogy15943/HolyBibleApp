package com.example.holybibleapp.domain

import com.example.holybibleapp.data.BooksDataToDomain
import com.example.holybibleapp.data.BooksRepository

interface BooksInteractor {
    suspend fun fetchBooks(): BooksDomain

    class Base(private val booksRepository: BooksRepository,
               private val booksDataToDomain: BooksDataToDomain): BooksInteractor {
        override suspend fun fetchBooks(): BooksDomain {
            val list = booksRepository.fetchBooks()
            return booksDataToDomain.map(list)
        }
    }
}
