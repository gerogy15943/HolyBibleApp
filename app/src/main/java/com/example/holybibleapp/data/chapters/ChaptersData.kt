package com.example.holybibleapp.data.chapters

import androidx.annotation.ChecksSdkIntAtLeast
import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.domain.chapters.ChaptersDomain
import java.lang.Exception

sealed class ChaptersData: Abstract.Object<ChaptersDomain, ChDataToDomainMapper> {
    data class Success(private val list: List<ChapterData>): ChaptersData(){
        override fun map(mapper: ChDataToDomainMapper): ChaptersDomain {
            return mapper.map(list)
        }

    }

    data class Fail(private val e: Exception): ChaptersData(){
        override fun map(mapper: ChDataToDomainMapper): ChaptersDomain {
            return mapper.map(e)
        }

    }
}