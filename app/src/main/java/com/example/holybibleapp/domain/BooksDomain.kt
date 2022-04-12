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
    class Success(private val list: List<BookData>,
                  private val bookDataToDomain: BookDataToDomainMapper): BooksDomain() {
        override fun map(mapper: BooksDomainToBooksUiMapper): BooksUi {
            val data = mutableListOf<BookDomain>()
            val ot = "OT"
            val nt = "NT"
            data.add(BookDomain.Testament(TestamentType.OLD))
            for(i in 0 until list.size){
                if(list.get(i).compareTestament(ot) && list.get(i+1).compareTestament(nt))
                    data.add(BookDomain.Testament(TestamentType.NEW))
                else
                    data.add(list.get(i).map(bookDataToDomain))
            }
            return mapper.map(data)
        }
    }

    class Fail(private val e: Exception): BooksDomain(){
        override fun map(mapper: BooksDomainToBooksUiMapper): BooksUi {
            Log.d("TAG", e.toString())
            val error = when(e){
                is UnknownHostException -> ErrorType.NO_CONNNECTION
                is HttpException -> ErrorType.SERVICE_UNAVALAIBLE
                else -> ErrorType.GENERIC_ERROR
            }
            return mapper.map(error)
        }

    }
}
