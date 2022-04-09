package com.example.holybibleapp.presentation

import com.example.holybibleapp.domain.BookDomainToUiMapper

class BaseBookDomainToUiMapper: BookDomainToUiMapper {
    override fun map(id: String, name: String): BookUi {
        return BookUi.Base(id, name)
    }
}