package com.example.holybibleapp.data.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.ChapterMapper
import com.example.holybibleapp.data.chapters.cache.ChapterDb


interface ChaptersMapper: Abstract.Mapper {
    fun mapToData(list: List<ChapterDb>): List<ChapterData>
    fun mapToDb(list: List<ChapterData>): List<ChapterDb>

    class Base(private val chapterToDb: ChapterMapper<ChapterDb>,
               private val chapterToData: ChapterMapper<ChapterData>
    ): ChaptersMapper {
        override fun mapToData(list: List<ChapterDb>): List<ChapterData> {
            return list.map {
                it.map(chapterToData)
            }
        }

        override fun mapToDb(list: List<ChapterData>): List<ChapterDb> {
            return list.map {
                it.mapToDb(chapterToDb)
            }
        }
    }
}