package com.example.holybibleapp.data.books

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.domain.books.BookDomain

interface BookDataToDomainMapper: Abstract.Mapper {
    fun map(id: String, name: String, testament: String): BookDomain

}