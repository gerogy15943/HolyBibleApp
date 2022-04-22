package com.example.holybibleapp.data.books.cache.mappers

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.books.cache.room.BookDb

interface BookDataToDbMapper: Abstract.Mapper {
    fun map(id: String, name: String, testament: String): BookDb

    class Base(): BookDataToDbMapper {
        override fun map(id: String, name: String, testament: String): BookDb {
            return BookDb(id, name, testament)
        }
    }
}