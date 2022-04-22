package com.example.holybibleapp.data.chapters.cache

import com.example.holybibleapp.core.ChapterMapper
import com.example.holybibleapp.core.RoomProvider
import com.example.holybibleapp.data.chapters.ChapterData
import com.example.holybibleapp.data.chapters.ChaptersMapper

interface ChaptersCacheDataSource {
    fun fetchChapters(bookId: String): List<ChapterDb>
    fun saveChapters(bookId: String, chapters: List<ChapterData>)

    class Base(
        private val roomProvider: RoomProvider,
        private val mapper: ChaptersMapper
        ): ChaptersCacheDataSource {
        override fun fetchChapters(bookId: String): List<ChapterDb> {
            val chapterDao = roomProvider.provide()?.ChapterDao()
            return chapterDao?.fetchChapters(bookId) ?: emptyList()
        }

        override fun saveChapters(bookId: String, chapters: List<ChapterData>) {
            val chapterDao = roomProvider.provide()?.ChapterDao()
            chapterDao?.insert(bookId, mapper.mapToDb(chapters))
        }
    }
}
