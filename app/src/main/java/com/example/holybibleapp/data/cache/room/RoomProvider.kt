package com.example.holybibleapp.data.cache.room

interface RoomProvider {
    fun provide(): AppDatabase?

    class Base(): RoomProvider {
        override fun provide(): AppDatabase? {
            return AppDatabase.getInstance()
        }

    }
}