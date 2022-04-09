package com.example.holybibleapp.data

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.domain.BookDomain

interface BookDataToDomainMapper: Abstract.Mapper {
    fun map(id: String, name: String): BookDomain
}