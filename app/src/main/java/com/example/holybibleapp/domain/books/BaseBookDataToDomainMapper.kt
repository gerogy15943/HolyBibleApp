package com.example.holybibleapp.domain.books

import com.example.holybibleapp.data.books.BookDataToDomainMapper

class BaseBookDataToDomainMapper(): BookDataToDomainMapper {
    override fun map(id: String, name: String, testament: String): BookDomain {
        return BookDomain.Base(id, name, testament)
    }
}