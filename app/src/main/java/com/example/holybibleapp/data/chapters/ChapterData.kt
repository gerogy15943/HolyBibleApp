package com.example.holybibleapp.data.chapters

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.ChapterMapper
import com.example.holybibleapp.core.ToDbMapper
import com.example.holybibleapp.data.chapters.cache.ChapterDb
import com.example.holybibleapp.domain.chapters.ChapterDomain

class ChapterData(private val id: String, private val bookId: String): Abstract.Object<ChapterDomain, ChapterMapper<ChapterDomain>>,
        ToDbMapper<ChapterDb, ChapterMapper<ChapterDb>>{

    override fun mapToDb(mapper: ChapterMapper<ChapterDb>): ChapterDb {
        return ChapterDb(id, bookId)
    }

    override fun map(mapper: ChapterMapper<ChapterDomain>): ChapterDomain {
        return ChapterDomain(id, bookId)
    }
        }


}

