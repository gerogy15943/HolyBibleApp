package com.example.holybibleapp.data.chapters.net

interface ChaptersCloudDataSource {
    suspend fun fetchChapters(bookId: String): List<ChapterCloud>

    class Base(
        private val service: ChaptersService): ChaptersCloudDataSource{
        override suspend fun fetchChapters(bookId: String): List<ChapterCloud> {
            return service.fetchChapters(bookId)
        }


    }
}