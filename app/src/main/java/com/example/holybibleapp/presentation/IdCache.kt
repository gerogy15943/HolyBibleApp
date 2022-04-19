package com.example.holybibleapp.presentation

import android.content.Context

interface IdCache{
    fun read(): Set<String>
    fun save(id: String)
    fun start()
    fun finish()

    class Base(private val context: Context): IdCache{
        private val idSet = mutableSetOf<String>()
        private val sharedPreferences = context.getSharedPreferences(ID_LIST_NAME, Context.MODE_PRIVATE)
        override fun read(): Set<String> {
            val set = sharedPreferences.getStringSet(ID_LIST_KEY, emptySet()) ?: emptySet()
            return set
        }

        override fun save(id: String) {
            idSet.add(id)
        }

        override fun start() {
            idSet.clear()
        }

        override fun finish() {
            sharedPreferences.edit().putStringSet(ID_LIST_KEY, idSet).apply()
        }

        private companion object{
            const val ID_LIST_NAME = "collapsedItemIdList"
            const val ID_LIST_KEY = "collapsedItemIdKey"
        }
    }
}