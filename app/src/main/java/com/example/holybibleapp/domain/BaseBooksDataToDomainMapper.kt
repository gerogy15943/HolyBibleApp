package com.example.holybibleapp.domain

import com.example.holybibleapp.data.BookData
import com.example.holybibleapp.data.BookDataToDomainMapper
import com.example.holybibleapp.data.BooksDataToDomainMapper
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException

class BaseBooksDataToDomainMapper(private val mapper: BookDataToDomainMapper): BooksDataToDomainMapper {
    override fun map(list: List<BookData>): BooksDomain {
        val data = mutableListOf<BookDomain>()
       val ot = "OT"
        val nt = "NT"
        data.add(BookDomain.Testament(TestamentType.OLD))
        for(i in 0 until list.size){
            if(list.get(i).compareTestament(ot) && list.get(i+1).compareTestament(nt)) {
                data.add(list.get(i).map(mapper))
                data.add(BookDomain.Testament(TestamentType.NEW))
            }
            else {
                data.add(list.get(i).map(mapper))
            }
        }
        return BooksDomain.Success(data)
    }

    override fun map(e:Exception): BooksDomain {
        val errorType = when(e){
            is UnknownHostException -> ErrorType.NO_CONNNECTION
            is HttpException -> ErrorType.SERVICE_UNAVALAIBLE
            else -> ErrorType.GENERIC_ERROR
        }
        return BooksDomain.Fail(errorType)
    }
}