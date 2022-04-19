package com.example.holybibleapp.presentation

import android.util.Log
import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.Matcher
import com.example.holybibleapp.presentation.BookUi.*

sealed class BookUi: Abstract.Object<Unit, StringMapper>, Collapsing, Comparing {

    override fun map(mapper: StringMapper) = Unit

    open fun changeState(): BookUi{
        return Empty
    }

    open fun saveId(idCache: IdCache) = Unit

    object Empty: BookUi()
    object Progress: BookUi()

    abstract class Info(
        protected open val id: String,
        protected open val name: String): BookUi(), Matcher<String> {
        override fun map(mapper: StringMapper) {
            mapper.map(name)
        }

        override fun matches(arg: String): Boolean {
            return arg == id
        }
    }

    data class Base(override val id: String, override val name: String): Info(id,name){
        override fun same(bookUi: BookUi): Boolean {
            return bookUi is Base && id == bookUi.id
        }

        override fun sameContent(bookUi: BookUi): Boolean {
             return if(bookUi is Base){
                name == bookUi.name
            }
            else false
        }
    }
    data class Testament(override val id: String, override val name: String,
                         private val collapsed: Boolean = false ): Info(id, name){
        override fun saveId(idCache: IdCache) {
            idCache.save(id)
        }

        override fun same(bookUi: BookUi): Boolean {
            return bookUi is Testament && id == bookUi.id
        }

        override fun sameContent(bookUi: BookUi): Boolean {
            return if (bookUi is Testament) {
                name == bookUi.name && collapsed == bookUi.collapsed
            } else false
        }

        override fun changeState(): BookUi {
            return Testament(id, name, !collapsed)
        }

        override fun collapseOrExpanded(listener: BibleAdapter.CollapseListener) {
            listener.collapseOrExpanded(id)
        }

        override fun showCollapsed(mapper: CollapseMapper) {
            mapper.show(collapsed)
        }

        override fun isCollapsed(): Boolean {
            return  collapsed
        }
    }

    data class Fail(private val message: String): BookUi() {
        override fun same(bookUi: BookUi): Boolean {
            return sameContent(bookUi)
        }

        override fun sameContent(bookUi: BookUi): Boolean {
            return if (bookUi is Fail)
                message == bookUi.message
            else false
        }

        override fun map(mapper: StringMapper) {
            mapper.map(message)
        }
    }

    interface StringMapper: Abstract.Mapper{
        fun map(text: String)
    }

    interface CollapseMapper: Abstract.Mapper{
        fun show(collapsed: Boolean)
    }
}

interface Collapsing{
    fun collapseOrExpanded(listener: BibleAdapter.CollapseListener) {}
    fun showCollapsed(mapper: CollapseMapper){}
    fun isCollapsed() = false
}

interface Comparing{
    fun same(bookUi: BookUi) = false
    fun sameContent(bookUi: BookUi) = false
}