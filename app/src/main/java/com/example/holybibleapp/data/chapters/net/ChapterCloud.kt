package com.example.holybibleapp.data.chapters.net

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.chapters.ChapterData
import com.google.gson.annotations.SerializedName
import java.lang.IllegalStateException

data class ChapterCloud(
    @SerializedName("id")
    private val id: String
): Abstract.Object<ChapterData, ChapterCloudToDataMapper> {
    override fun map(mapper: ChapterCloudToDataMapper): ChapterData {
        throw IllegalStateException()
    }
    fun map(bookId: String, mapper: ChapterCloudToDataMapper): ChapterData{
        return mapper.map(id, bookId)
    }
}

data class ChapterCloudWrapper(
    private val chapterCloud: ChapterCloud,
    private val bookId: String): Abstract.Object<ChapterData, ChapterCloudToDataMapper>{
    override fun map(mapper: ChapterCloudToDataMapper): ChapterData {
        chapterCloud.map(bookId, mapper)
    }

}
