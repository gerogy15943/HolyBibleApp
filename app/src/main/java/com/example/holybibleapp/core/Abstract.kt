package com.example.holybibleapp.core

import com.example.holybibleapp.domain.books.ErrorType
import retrofit2.HttpException
import java.net.UnknownHostException

abstract class Abstract {

    interface Object<T, M: Mapper> {
       fun map(mapper: M): T

       interface UnitObject<E:Mapper>: Object<UnitObject<E>, E>
    }
    interface Mapper{
        object Empty: Mapper

        interface Data<S, R>: Mapper{
            fun map(data: S) : R
        }

        interface DataToDomain<S, R>: Data<S, R>{
            fun map(e: Exception): R

            abstract class Base<S, R>: DataToDomain<S, R>{
                protected fun errorType(e: Exception): ErrorType {
                    return when(e){
                        is UnknownHostException -> ErrorType.NO_CONNNECTION
                        is HttpException -> ErrorType.SERVICE_UNAVALAIBLE
                        else -> ErrorType.GENERIC_ERROR
                    }
                }
            }
        }
    }

}