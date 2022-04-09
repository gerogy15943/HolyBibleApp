package com.example.holybibleapp.domain

import com.example.holybibleapp.data.BookData
import com.example.holybibleapp.data.BookDataToDomainMapper
import com.example.holybibleapp.data.BooksDataToDomainMapper
import java.lang.Exception

class BaseBooksDataToDomainMapper(private val mapper: BookDataToDomainMapper): BooksDataToDomainMapper {
    override fun map(list: List<BookData>): BooksDomain {
        return BooksDomain.Success(list,mapper)
    }

    override fun map(e: Exception): BooksDomain {
        return BooksDomain.Fail(e)
    }
}