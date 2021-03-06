package com.example.holybibleapp.presentation

import android.util.Log
import com.example.holybibleapp.R
import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.domain.BookDomain
import com.example.holybibleapp.domain.BookDomainToUiMapper
import com.example.holybibleapp.domain.ErrorType

sealed class BooksUi: Abstract.Object<Unit, BooksCommunication>{
    abstract fun cache(uiDataCache: UiDataCache): BooksUi

    data class Base(private val list: List<BookUi> ): BooksUi() {
        override fun map(mapper: BooksCommunication) {
            Log.d("TAG", list.get(0).toString())
            mapper.map(list)
        }
        override fun cache(uiDataCache: UiDataCache): BooksUi{
            return uiDataCache.cache(list)
        }
    }
}
