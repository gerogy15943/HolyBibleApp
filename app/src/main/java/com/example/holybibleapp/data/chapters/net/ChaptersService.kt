package com.example.holybibleapp.data.chapters.net

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface ChaptersService {
    @GET("books/{id}/chapters")
    fun fetchChapters(
        @Path("id") bookId: String
    ): List<ChapterCloud>
}