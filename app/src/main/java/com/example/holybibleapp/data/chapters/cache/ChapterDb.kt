package com.example.holybibleapp.data.chapters.cache

import androidx.room.Entity
import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.ChapterMapper
import com.example.holybibleapp.data.chapters.ChapterData

@Entity
data class ChapterDb(
    private val id: String,
    private val bookId: String
): Abstract.Object<ChapterData, ChapterMapper<ChapterData>> {
    override fun map(mapper: ChapterMapper<ChapterData>): ChapterData {
        return mapper.map(id, bookId)
    }

}
