package com.example.holybibleapp.domain

import com.example.holybibleapp.core.Book
import com.example.holybibleapp.data.BooksDataToDomainMapper
import java.lang.Exception

class BaseBooksDataToDomainMapper: BooksDataToDomainMapper {
    override fun map(list: List<Book>): BooksDomain {
        return BooksDomain.Success(list)
    }

    override fun map(e: Exception): BooksDomain {
        return BooksDomain.Fail(e)
    }
}