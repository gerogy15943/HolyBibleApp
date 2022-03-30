package com.example.holybibleapp.presentation

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.Book
import com.example.holybibleapp.data.net.BookServerModel
import java.lang.Exception

sealed class BookUi: Abstract.Object<Unit, Abstract.Mapper.Empty>(){
    class Success(private val list: List<Book>): BookUi{
        override fun map(mapper: Abstract.Mapper.Empty) {
            TODO("Not yet implemented")
        }

    }
    class Fail(e: Exception): BookUi{
        override fun map(mapper: Abstract.Mapper.Empty) {
            TODO("Not yet implemented")
        }

    }
}
