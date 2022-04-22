package com.example.holybibleapp.data.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.books.BookData
import com.example.holybibleapp.domain.books.BooksDomain
import com.example.holybibleapp.domain.chapters.ChaptersDomain
import java.lang.Exception

interface ChDataToDomainMapper: Abstract.Mapper {
    fun map(list: List<ChapterData>): ChaptersDomain
    fun map(e: Exception): ChaptersDomain
}
