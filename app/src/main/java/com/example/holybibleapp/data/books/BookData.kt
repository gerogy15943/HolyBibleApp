package com.example.holybibleapp.data.books

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.ToDbMapper
import com.example.holybibleapp.data.books.cache.mappers.BookDataToDbMapper
import com.example.holybibleapp.data.books.cache.room.BookDb
import com.example.holybibleapp.domain.books.BookDomain

data class BookData(private val id: String, private val name: String, private val testament: String):
    ToDbMapper<BookDb, BookDataToDbMapper>,
    Abstract.Object<BookDomain, BookDataToDomainMapper>{
    override fun map(mapper: BookDataToDomainMapper): BookDomain {
        return mapper.map(id, name, testament)
    }

    fun compareTestament(cTestament: String): Boolean{
        return testament == cTestament
    }

    override fun mapToDb(mapper: BookDataToDbMapper): BookDb {
        return mapper.map(id, name, testament)
    }
}
