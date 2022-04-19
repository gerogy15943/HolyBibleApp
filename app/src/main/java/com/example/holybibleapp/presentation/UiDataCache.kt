package com.example.holybibleapp.presentation

import android.content.Context
import android.util.Log

interface UiDataCache {

    fun cache(list: List<BookUi>): BooksUi
    fun changeState(id: String): List<BookUi>
    fun saveState()

    class Base(private val idCache: IdCache) : UiDataCache{
        private val cacheList = ArrayList<BookUi>()
        override fun cache(list: List<BookUi>): BooksUi {
            cacheList.clear()
            cacheList.addAll(list)
            var newList: List<BookUi> = ArrayList(list)
            var ids = idCache.read()
            ids.forEach { id ->
                newList = changeState(id)
            }
            return BooksUi.Base(newList)
        }

        override fun changeState(id: String): List<BookUi> {
            val newList = ArrayList<BookUi>()
            val item = cacheList.find {
                (it as BookUi.Info).matches(id)
            }
            var add = false
            cacheList.forEachIndexed { index, book ->
                if (book is BookUi.Testament) {
                    if (item == book) {
                        val element = book.changeState()
                        cacheList.set(index, element)
                        newList.add(element)
                        add = !element.isCollapsed()
                    } else {
                        newList.add(book)
                        add = !book.isCollapsed()
                    }
                } else {
                    if (add) newList.add(book)
                }
            }
            return newList
        }

        override fun saveState() {
            idCache.start()
            cacheList.filter {
                it.isCollapsed()
            }.map {
                it.saveId(idCache)
            }
            idCache.finish()
        }

    }
}
