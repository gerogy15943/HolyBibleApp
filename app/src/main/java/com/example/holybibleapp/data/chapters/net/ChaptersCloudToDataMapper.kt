package com.example.holybibleapp.data.chapters.net

import com.example.holybibleapp.data.chapters.ChapterData
import com.example.holybibleapp.data.chapters.ChaptersData

interface ChaptersCloudToDataMapper {
    fun map(cloudList: List<ChapterCloud>, bookId: String): List<ChapterData>

    class Base(private val mapper: ChapterCloudToDataMapper): ChaptersCloudToDataMapper{
        override fun map(cloudList: List<ChapterCloud>, bookId: String): List<ChapterData> {
            return cloudList.map {
                ChapterCloudWrapper(it, bookId).map(mapper)
            }
        }
    }
}