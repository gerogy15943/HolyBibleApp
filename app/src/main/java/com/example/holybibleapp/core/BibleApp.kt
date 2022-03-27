package com.example.holybibleapp.core

import android.app.Application
import com.example.holybibleapp.data.BookRepository
import com.example.holybibleapp.data.BooksCloudMapper
import com.example.holybibleapp.data.cache.mappers.BooksCacheDataSource
import com.example.holybibleapp.data.cache.mappers.BooksCacheMapper
import com.example.holybibleapp.data.cache.room.AppDatabase
import com.example.holybibleapp.data.net.BookService
import com.example.holybibleapp.data.net.BooksCloudDataSource
import retrofit2.Retrofit

class BibleApp: Application() {

    private companion object{
        const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
    }

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .build()
        val service = retrofit.create(BookService::class.java)

        AppDatabase.initDatabase(applicationContext)

        val booksCacheDataSource = BooksCacheDataSource.Base()
        val booksCacheMapper = BooksCacheMapper.Base()
        val booksCloudDataSource = BooksCloudDataSource.Base()
        val booksCloudMapper = BooksCloudMapper.Base()

        val bookRepository = BookRepository.Base(
            booksCloudDataSource,
            booksCloudMapper,
            booksCacheDataSource,
            booksCacheMapper
        )
    }
}