package com.example.holybibleapp.data.cache.mappers

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.Book

interface BookDbToBookMapper: Abstract.Mapper {
    fun map(id: Int, name: String): Book

    class Base(): BookDbToBookMapper {
        override fun map(id: Int, name: String): Book {
            return Book(id, name)
        }

    }
}
