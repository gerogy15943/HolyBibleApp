package com.example.holybibleapp.presentation

import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Test
import java.util.*

class IdCacheSaveTest {

    @Test
    fun test_empty_id(){
        val idCache = TestIdCache()
        val uiDataCache = UiDataCache.Base(idCache)
        val actual = uiDataCache.cache(listOf(
            BookUi.Testament("old", "old Testament"),
            BookUi.Base("1", "1")
        ))

        val expected = BooksUi.Base(listOf(
            BookUi.Testament("old", "old Testament"),
            BookUi.Base("1", "1")
        ))

        assertEquals(expected, actual)
    }

    @Test
    fun test_not_empty_id(){
        val idCache = TestIdCache()
        idCache.save("old")
        val uiDataCache = UiDataCache.Base(idCache)
        val actual = uiDataCache.cache(listOf(
            BookUi.Testament("old", "old Testament"),
            BookUi.Base("1", "1"),
            BookUi.Testament("new", "new Testament"),
            BookUi.Base("2", "2"),
        ))

        val expected = BooksUi.Base(listOf(
            BookUi.Testament("old", "old Testament", true),
            BookUi.Testament("new", "new Testament"),
            BookUi.Base("2", "2")
        ))

        assertEquals(expected, actual)
    }

    @Test
    fun test_not_empty_two_id(){
        val idCache = TestIdCache()
        idCache.save("old")
        idCache.save("new")
        val uiDataCache = UiDataCache.Base(idCache)
        val actual = uiDataCache.cache(listOf(
            BookUi.Testament("old", "old Testament"),
            BookUi.Base("1", "1"),
            BookUi.Testament("new", "new Testament"),
            BookUi.Base("2", "2"),
        ))

        val expected = BooksUi.Base(listOf(
            BookUi.Testament("old", "old Testament", true),
            BookUi.Testament("new", "new Testament", true),
        ))

        assertEquals(expected, actual)
    }


    inner class TestIdCache: IdCache {

        private val set = HashSet<String>()
        override fun read(): Set<String> {
            return set
        }

        override fun save(id: String) {
            set.add(id)
        }

        override fun start() {
            set.clear()
        }

        override fun finish() = Unit
    }
}