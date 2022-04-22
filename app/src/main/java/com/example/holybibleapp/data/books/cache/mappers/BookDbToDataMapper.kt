package com.example.holybibleapp.data.books.cache.mappers

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.books.BookData

interface BookDbToDataMapper: Abstract.Mapper {
    fun map(id: String, name: String, testament: String): BookData

    class Base(): BookDbToDataMapper {
        override fun map(id: String, name: String, testament: String): BookData {
            return BookData(id, name, testament)
        }
    }
}
