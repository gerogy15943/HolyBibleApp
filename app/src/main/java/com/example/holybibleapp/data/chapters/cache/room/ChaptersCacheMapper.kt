package com.example.holybibleapp.data.chapters.cache.room

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.ChapterMapper
import com.example.holybibleapp.data.chapters.ChDataToDb
import com.example.holybibleapp.data.chapters.ChapterData
import com.example.holybibleapp.data.chapters.cache.ChapterDb

interface ChaptersCacheMapper: Abstract.Mapper {
    fun mapToDb(list: List<ChapterData>): List<ChapterDb>
    fun map(list: List<ChapterDb>): List<ChapterData>

    class Base(
        private val mapperToDb: ChapterMapper<ChapterDb>,
        private val mapperToData: ChapterMapper<ChapterData>
    ): ChaptersCacheMapper{
        override fun mapToDb(list: List<ChapterData>): List<ChapterDb> {
            return list.map {
                it.mapToDb(mapperToDb)
            }
        }

        override fun map(list: List<ChapterDb>): List<ChapterData> {
            return list.map {
                it.map(mapperToData)
            }
        }
    }
}