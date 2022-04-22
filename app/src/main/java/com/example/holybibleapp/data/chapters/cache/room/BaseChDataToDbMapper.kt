package com.example.holybibleapp.data.chapters.cache.room

import com.example.holybibleapp.core.ChapterMapper
import com.example.holybibleapp.data.chapters.ChapterData

class BaseChDataToDbMapper: ChapterMapper<ChapterData> {
    override fun map(id: String, bookId: String): ChapterData {
        return ChapterData(id, bookId)
    }
}