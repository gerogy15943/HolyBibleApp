package com.example.holybibleapp.data.cache.mappers

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.BookData

interface BookDbToDataMapper: Abstract.Mapper {
    fun map(id: String, name: String, testament: String): BookData

    class Base(): BookDbToDataMapper {
        override fun map(id: String, name: String, testament: String): BookData {
            return BookData(id, name, testament)
        }
    }
}
