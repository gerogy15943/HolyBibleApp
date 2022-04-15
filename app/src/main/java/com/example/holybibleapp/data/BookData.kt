package com.example.holybibleapp.data

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.cache.mappers.BookDataToDbMapper
import com.example.holybibleapp.data.cache.room.BookDb
import com.example.holybibleapp.domain.BookDomain
import kotlinx.coroutines.internal.LockFreeLinkedListNode

data class BookData(private val id: String, private val name: String, private val testament: String):
    ToBookDb<BookDb,BookDataToDbMapper>,
    Abstract.Object<BookDomain, BookDataToDomainMapper>{
    override fun map(mapper: BookDataToDomainMapper): BookDomain {
        return mapper.map(id, name, testament)
    }

    override fun mapTo(mapper: BookDataToDbMapper): BookDb {
        return mapper.map(id, name, testament)
    }

    fun compareTestament(cTestament: String): Boolean{
        return testament == cTestament
    }
}
interface ToBookDb<T, M: Abstract.Mapper>{
    fun mapTo(mapper: M): T
}