package com.example.holybibleapp.data.chapters

import com.example.holybibleapp.data.books.BooksData
import com.example.holybibleapp.data.chapters.cache.ChaptersCacheDataSource
import com.example.holybibleapp.data.chapters.cache.room.ChaptersCacheMapper
import com.example.holybibleapp.data.chapters.net.ChaptersCloudDataSource
import com.example.holybibleapp.data.chapters.net.ChaptersCloudToDataMapper
import java.lang.Exception

interface ChaptersRepository {

    suspend fun fetchChapters(bookId: String): ChaptersData

    class Base(
        private val cloudDataSource: ChaptersCloudDataSource,
        private val cacheDataSource: ChaptersCacheDataSource,
        private val cloudMapper: ChaptersCloudToDataMapper,
        private val cacheMapper: ChaptersCacheMapper
    ): ChaptersRepository {
        override suspend fun fetchChapters(bookId: String): ChaptersData {
            try{
                var chapterCacheList = cacheDataSource.fetchChapters(bookId)
                if(chapterCacheList.isEmpty()){
                    val booksCloudList = cloudDataSource.fetchChapters(bookId)
                    val books = cloudMapper.map(booksCloudList, bookId)
                    cacheDataSource.saveChapters(bookId, books)
                }
                chapterCacheList = cacheDataSource.fetchChapters(bookId)
                return ChaptersData.Success(cacheMapper.map(chapterCacheList))
            } catch (e: Exception){return ChaptersData.Fail(e)
            }
        }
    }
}
