package com.example.holybibleapp.domain

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.presentation.BookUi

interface BookDomainToUiMapper: Abstract.Mapper {
    fun map(id: String, name: String): BookUi
}