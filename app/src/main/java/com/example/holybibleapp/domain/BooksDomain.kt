package com.example.holybibleapp.domain

import android.util.Log
import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.BookData
import com.example.holybibleapp.data.BookDataToDomainMapper
import com.example.holybibleapp.presentation.BooksUi
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException

sealed class BooksDomain: Abstract.Object<BooksUi, BooksDomainToBooksUiMapper>{
    data class Success(private val list: List<BookDomain>): BooksDomain() {
        override fun map(mapper: BooksDomainToBooksUiMapper): BooksUi {
            return mapper.map(list)
        }
    }

    data class Fail(private val errorType: ErrorType): BooksDomain(){
        override fun map(mapper: BooksDomainToBooksUiMapper): BooksUi {
            return mapper.map(errorType)
        }

    }
}
