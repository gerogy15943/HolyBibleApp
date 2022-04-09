package com.example.holybibleapp.domain

import com.example.holybibleapp.data.BookDataToDomainMapper

class BaseBookDataToDomainMapper: BookDataToDomainMapper {
    override fun map(id: String, name: String): BookDomain {
        return BookDomain(id, name)
    }

}