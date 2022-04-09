package com.example.holybibleapp.data.cache.mappers

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.cache.room.BookDb

interface BookDataToDbMapper: Abstract.Mapper {
    fun map(id: String, name: String): BookDb

    class Base(): BookDataToDbMapper {
        override fun map(id: String, name: String): BookDb {
            return BookDb(id, name)
        }
    }
}