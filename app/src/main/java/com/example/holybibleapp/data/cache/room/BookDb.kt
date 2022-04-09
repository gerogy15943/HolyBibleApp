package com.example.holybibleapp.data.cache.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.BookData
import com.example.holybibleapp.data.cache.mappers.BookDbToDataMapper

@Entity
data class BookDb(
    @PrimaryKey
    val id: String,
    @ColumnInfo
    val name: String
): Abstract.Object<BookData, BookDbToDataMapper> {
    override fun map(toBookDbToDataMapper: BookDbToDataMapper): BookData {
        return toBookDbToDataMapper.map(id, name)
    }
}
