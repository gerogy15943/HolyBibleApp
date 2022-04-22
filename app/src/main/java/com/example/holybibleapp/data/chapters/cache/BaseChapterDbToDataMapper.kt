package com.example.holybibleapp.data.chapters.cache

import com.example.holybibleapp.core.ChapterMapper
import com.example.holybibleapp.data.chapters.ChapterData

class BaseChapterDbToDataMapper: ChapterMapper<ChapterData> {
    override fun map(id: String, bookId: String): ChapterData {
        return ChapterData(id, bookId)
    }
}