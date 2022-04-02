package com.example.holybibleapp.data.net

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.Book

interface BookServerToBookMapper: Abstract.Mapper {
    fun map(id: String, name: String): Book

    class Base(): BookServerToBookMapper{
        override fun map(id: String, name: String): Book {
            return Book(id, name)
        }

    }
}
