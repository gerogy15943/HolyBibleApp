package com.example.holybibleapp.core

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.holybibleapp.data.books.cache.room.BookDao
import com.example.holybibleapp.data.books.cache.room.BookDb
import com.example.holybibleapp.data.chapters.cache.room.ChapterDao

@Database(entities = [BookDb::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun BookDao(): BookDao
    abstract fun ChapterDao(): ChapterDao

    companion object{
        private var instance: AppDatabase? = null

        fun initDatabase(context: Context){
            if(instance == null){
                instance = Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java, "database")
                    .build()
            }
        }

        fun getInstance(): AppDatabase?{
            return instance
        }
    }
}
