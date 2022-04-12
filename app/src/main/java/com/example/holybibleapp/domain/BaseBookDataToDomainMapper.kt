package com.example.holybibleapp.domain

import com.example.holybibleapp.R
import com.example.holybibleapp.data.BookDataToDomainMapper
import com.example.holybibleapp.presentation.ResourceProvider

class BaseBookDataToDomainMapper(): BookDataToDomainMapper {
    override fun map(id: String, name: String, testament: String): BookDomain {
        return BookDomain.Base(id, name, testament)
    }
}