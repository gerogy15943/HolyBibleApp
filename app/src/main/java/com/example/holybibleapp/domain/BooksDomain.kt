package com.example.holybibleapp.domain

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.Book
import com.example.holybibleapp.presentation.BooksUi
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException

sealed class BooksDomain: Abstract.Object<BooksUi, BooksDomainToBooksUiMapper>(){
    class Success(private val list: List<Book>): BooksDomain() {
        override fun map(mapper: BooksDomainToBooksUiMapper): BooksUi {
            return mapper.map(list)
        }
    }

    class Fail(private val e: Exception): BooksDomain(){
        override fun map(mapper: BooksDomainToBooksUiMapper): BooksUi {
            val error = when(e){
                is UnknownHostException -> ErrorType.NO_CONNNECTION
                is HttpException -> ErrorType.SERVICE_UNAVALAIBLE
                else -> ErrorType.GENERIC_ERROR
            }
            return mapper.map(error)
        }

    }
}
