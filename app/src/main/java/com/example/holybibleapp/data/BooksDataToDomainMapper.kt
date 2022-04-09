package com.example.holybibleapp.data

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.net.BookServerModel
import com.example.holybibleapp.domain.BookDomain
import com.example.holybibleapp.domain.BooksDomain
import java.lang.Exception

interface BooksDataToDomainMapper: Abstract.Mapper {
    fun map(list: List<BookData>): BooksDomain
    fun map(e: Exception): BooksDomain

}
