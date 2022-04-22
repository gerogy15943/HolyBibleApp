package com.example.holybibleapp.core

interface ToDbMapper<T, M: Abstract.Mapper> {
    fun mapToDb(mapper: M): T

}