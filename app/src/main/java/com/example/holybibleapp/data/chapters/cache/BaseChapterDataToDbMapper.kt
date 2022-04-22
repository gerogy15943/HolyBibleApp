package com.example.holybibleapp.data.chapters.cache

import com.example.holybibleapp.core.ChapterMapper

class BaseChapterDataToDbMapper: ChapterMapper<ChapterDb> {
    override fun map(id: String, bookId: String): ChapterDb {
        return ChapterDb(id, bookId)
    }
}