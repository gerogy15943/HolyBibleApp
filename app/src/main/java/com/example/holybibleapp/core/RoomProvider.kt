package com.example.holybibleapp.core

interface RoomProvider {
    fun provide(): AppDatabase?

    class Base(): RoomProvider {
        override fun provide(): AppDatabase? {
            return AppDatabase.getInstance()
        }

    }
}