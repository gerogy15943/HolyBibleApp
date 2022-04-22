package com.example.holybibleapp.data.chapters.net

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.chapters.ChapterData

interface ChapterCloudToDataMapper: Abstract.Mapper {
    fun map(id: String, bookId: String): ChapterData

    class Base: ChapterCloudToDataMapper {
        override fun map(id: String, bookId: String): ChapterData {
            return ChapterData(id, bookId)
        }
    }
}
