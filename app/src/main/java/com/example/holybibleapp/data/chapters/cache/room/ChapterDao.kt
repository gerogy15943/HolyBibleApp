package com.example.holybibleapp.data.chapters.cache.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.holybibleapp.data.chapters.cache.ChapterDb

@Dao
interface ChapterDao {

    @Query("SELECT * FROM chapterDb WHERE bookId LIKE :bookId")
    fun fetchChapters(bookId: String): List<ChapterDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bookId: String, chapters: List<ChapterDb>)
}