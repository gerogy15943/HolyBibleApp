package com.example.holybibleapp.data.books.net

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.books.BookData

interface BookServerToDataMapper: Abstract.Mapper {
    fun map(id: String, name: String, testament: String): BookData

    class Base(): BookServerToDataMapper {
        override fun map(id: String, name: String, testament: String): BookData {
            return BookData(id, name, testament)
        }
    }
}
