package com.example.holybibleapp.data.net

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.BookData

interface BookServerToDataMapper: Abstract.Mapper {
    fun map(id: String, name: String): BookData

    class Base(): BookServerToDataMapper{
        override fun map(id: String, name: String): BookData {
            return BookData(id, name)
        }
    }
}
