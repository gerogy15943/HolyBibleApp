package com.example.holybibleapp.data.cache.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BookDb::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun BookDao(): BookDao

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
