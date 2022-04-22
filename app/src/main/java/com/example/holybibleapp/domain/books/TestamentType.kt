package com.example.holybibleapp.domain.books

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.Matcher
import com.example.holybibleapp.presentation.BookUi

enum class TestamentType(private val id: String): Abstract.Object<BookUi, BookDomainToUiMapper>,
    Matcher<String>{
    OLD("old"),
    NEW("new");

    override fun map(mapper: BookDomainToUiMapper): BookUi {
        return mapper.map(id, name)
    }

    override fun matches(id: String): Boolean{
       return this.id == id
    }
}

