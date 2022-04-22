package com.example.holybibleapp.core

interface ChapterMapper<T>: Abstract.Mapper {
    fun map(id: String, bookId: String) : T
}