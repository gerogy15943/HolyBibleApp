package com.example.holybibleapp.data.cache.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookDao {
    @Query("SELECT * FROM bookDb")
    fun fetchBooks(): List<BookDb>

    @Query("SELECT * FROM bookdb ORDER BY testament DESC")
    fun fetchSortBooks(): List<BookDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<BookDb>)
}