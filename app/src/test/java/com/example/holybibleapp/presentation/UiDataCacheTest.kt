package com.example.holybibleapp.presentation

import org.junit.Assert.*
import org.junit.Test
import java.util.HashSet

class UiDataCacheTest {

    private val testIdCache = TestIdCache()
    private val source = listOf(
        BookUi.Testament("old", "Old Testament"),
        BookUi.Base("1", "First"),
        BookUi.Base("2", "Second"),
        BookUi.Testament("new", "New Testament"),
        BookUi.Base("3", "Third"),
        BookUi.Base("4", "Forth")
    )

    @Test
    fun test_collapse_first() {
        val dataCache = UiDataCache.Base(testIdCache)
        dataCache.cache(source)

        val actual = dataCache.changeState("old")
        val expected = listOf(
            BookUi.Testament("old", "Old Testament", true),
            BookUi.Testament("new", "New Testament"),
            BookUi.Base("3", "Third"),
            BookUi.Base("4", "Forth")
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_collapse_second() {
        val dataCache = UiDataCache.Base(testIdCache)
        dataCache.cache(source)

        val actual = dataCache.changeState("new")
        val expected = listOf(
            BookUi.Testament("old", "Old Testament"),
            BookUi.Base("1", "First"),
            BookUi.Base("2", "Second"),
            BookUi.Testament("new", "New Testament", true)
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_collapse_first_then_expand() {
        val dataCache = UiDataCache.Base(testIdCache)
        dataCache.cache(source)

        var actual = dataCache.changeState("old")
        var expected = listOf(
            BookUi.Testament("old", "Old Testament", true),
            BookUi.Testament("new", "New Testament"),
            BookUi.Base("3", "Third"),
            BookUi.Base("4", "Forth")
        )
        assertEquals(expected, actual)

        actual = dataCache.changeState("old")
        expected = listOf(
            BookUi.Testament("old", "Old Testament"),
            BookUi.Base("1", "First"),
            BookUi.Base("2", "Second"),
            BookUi.Testament("new", "New Testament"),
            BookUi.Base("3", "Third"),
            BookUi.Base("4", "Forth")
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_collapse_second_then_expand() {
        val dataCache = UiDataCache.Base(testIdCache)
        dataCache.cache(source)

        var actual = dataCache.changeState("new")
        var expected = listOf(
            BookUi.Testament("old", "Old Testament"),
            BookUi.Base("1", "First"),
            BookUi.Base("2", "Second"),
            BookUi.Testament("new", "New Testament", true)

        )
        assertEquals(expected, actual)

        actual = dataCache.changeState("new")
        expected = listOf(
            BookUi.Testament("old", "Old Testament"),
            BookUi.Base("1", "First"),
            BookUi.Base("2", "Second"),
            BookUi.Testament("new", "New Testament"),
            BookUi.Base("3", "Third"),
            BookUi.Base("4", "Forth")
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_collapse_first_collapse_second() {
        val dataCache = UiDataCache.Base(testIdCache)
        dataCache.cache(source)

        var actual = dataCache.changeState("old")
        var expected = listOf(
            BookUi.Testament("old", "Old Testament", true),
            BookUi.Testament("new", "New Testament"),
            BookUi.Base("3", "Third"),
            BookUi.Base("4", "Forth")
        )
        assertEquals(expected, actual)

        actual = dataCache.changeState("new")
        expected = listOf(
            BookUi.Testament("old", "Old Testament", true),
            BookUi.Testament("new", "New Testament", true)
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_collapse_first_collapse_second_expand_first() {
        val dataCache = UiDataCache.Base(testIdCache)
        dataCache.cache(source)

        var actual = dataCache.changeState("old")
        var expected = listOf(
            BookUi.Testament("old", "Old Testament", true),
            BookUi.Testament("new", "New Testament"),
            BookUi.Base("3", "Third"),
            BookUi.Base("4", "Forth")
        )
        assertEquals(expected, actual)

        actual = dataCache.changeState("new")
        expected = listOf(
            BookUi.Testament("old", "Old Testament", true),
            BookUi.Testament("new", "New Testament", true)
        )
        assertEquals(expected, actual)

        actual = dataCache.changeState("old")
        expected = listOf(
            BookUi.Testament("old", "Old Testament"),
            BookUi.Base("1", "First"),
            BookUi.Base("2", "Second"),
            BookUi.Testament("new", "New Testament", true),
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_collapse_second_collapse_first_expand_second() {
        val dataCache = UiDataCache.Base(testIdCache)
        dataCache.cache(source)

        var actual = dataCache.changeState("new")
        var expected = listOf(
            BookUi.Testament("old", "Old Testament"),
            BookUi.Base("1", "First"),
            BookUi.Base("2", "Second"),
            BookUi.Testament("new", "New Testament", true)
        )
        assertEquals(expected, actual)

        actual = dataCache.changeState("old")
        expected = listOf(
            BookUi.Testament("old", "Old Testament", true),
            BookUi.Testament("new", "New Testament", true)
        )
        assertEquals(expected, actual)


        actual = dataCache.changeState("new")
        expected = listOf(
            BookUi.Testament("old", "Old Testament", true),
            BookUi.Testament("new", "New Testament"),
            BookUi.Base("3", "Third"),
            BookUi.Base("4", "Forth"),
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_collapse_second_collapse_first_expand_first_expand_second() {
        val dataCache = UiDataCache.Base(testIdCache)
        dataCache.cache(source)

        var actual = dataCache.changeState("old")
        var expected = listOf(
            BookUi.Testament("old", "Old Testament", true),
            BookUi.Testament("new", "New Testament"),
            BookUi.Base("3", "Third"),
            BookUi.Base("4", "Forth"),
        )
        assertEquals(expected, actual)

        actual = dataCache.changeState("new")
        expected = listOf(
            BookUi.Testament("old", "Old Testament", true),
            BookUi.Testament("new", "New Testament", true)
        )
        assertEquals(expected, actual)


        actual = dataCache.changeState("old")
        expected = listOf(
            BookUi.Testament("old", "Old Testament"),
            BookUi.Base("1", "First"),
            BookUi.Base("2", "Second"),
            BookUi.Testament("new", "New Testament", true)
        )
        assertEquals(expected, actual)

        actual = dataCache.changeState("new")
        expected = listOf(
            BookUi.Testament("old", "Old Testament"),
            BookUi.Base("1", "First"),
            BookUi.Base("2", "Second"),
            BookUi.Testament("new", "New Testament"),
            BookUi.Base("3", "Third"),
            BookUi.Base("4", "Forth")
        )
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