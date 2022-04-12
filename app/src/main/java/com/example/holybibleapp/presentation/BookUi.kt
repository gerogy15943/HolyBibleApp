package com.example.holybibleapp.presentation

import com.example.holybibleapp.core.Abstract

sealed class BookUi: Abstract.Object<Unit, BookUi.StringMapper> {

    override fun map(mapper: StringMapper) = Unit

    object Progress: BookUi()

    abstract class Info(
        private val id: String,
        private val name: String): BookUi(){
        override fun map(mapper: StringMapper) {
            mapper.map(name)
        }
        }

    class Base(id: String, name: String): Info(id,name)

    class Testament(id: String, name: String): Info(id, name)

    class Fail(private val message: String): BookUi() {
        override fun map(mapper: StringMapper) {
            mapper.map(message)
        }
    }

    interface StringMapper: Abstract.Mapper{
        fun map(text: String)
    }
}