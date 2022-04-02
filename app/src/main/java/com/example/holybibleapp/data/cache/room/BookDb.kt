package com.example.holybibleapp.data.cache.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.Book
import com.example.holybibleapp.data.cache.mappers.BookDbToBookMapper

@Entity
data class BookDb(
    @PrimaryKey
    val id: String,
    @ColumnInfo
    val name: String
): Abstract.Object<Book, BookDbToBookMapper>() {
    override fun map(toBookMapper: BookDbToBookMapper): Book {
        return toBookMapper.map(id, name)
    }

}
