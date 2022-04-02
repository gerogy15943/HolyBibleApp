package com.example.holybibleapp.core

import android.app.Application
import com.example.holybibleapp.data.BooksRepository
import com.example.holybibleapp.data.BooksCloudMapper
import com.example.holybibleapp.data.cache.mappers.BookDbToBookMapper
import com.example.holybibleapp.data.cache.mappers.BookToBookDbMapper
import com.example.holybibleapp.data.cache.mappers.BooksCacheDataSource
import com.example.holybibleapp.data.cache.mappers.BooksCacheMapper
import com.example.holybibleapp.data.cache.room.AppDatabase
import com.example.holybibleapp.data.cache.room.RoomProvider
import com.example.holybibleapp.data.net.BookServerToBookMapper
import com.example.holybibleapp.data.net.BookService
import com.example.holybibleapp.data.net.BooksCloudDataSource
import com.example.holybibleapp.domain.BaseBooksDataToDomainMapper
import retrofit2.Retrofit
import com.example.holybibleapp.domain.BooksInteractor
import com.example.holybibleapp.presentation.BaseBooksDomainMapper
import com.example.holybibleapp.presentation.BooksCommunication
import com.example.holybibleapp.presentation.MainViewModel
import com.example.holybibleapp.presentation.ResourceProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory


class BibleApp: Application() {

    lateinit var mainViewModel: MainViewModel


    private companion object{
        const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
    }

    private fun getOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    override fun onCreate() {
        super.onCreate()


        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(BookService::class.java)

        AppDatabase.initDatabase(applicationContext)

        val bookDbToBookMapper = BookDbToBookMapper.Base()
        val bookToBookDbMapper = BookToBookDbMapper.Base()
        val booksCacheMapper = BooksCacheMapper.Base(bookDbToBookMapper, bookToBookDbMapper)
        val booksCloudDataSource = BooksCloudDataSource.Base(service)
        val bookServerToBookMapper = BookServerToBookMapper.Base()
        val booksCloudMapper = BooksCloudMapper.Base(bookServerToBookMapper)
        val roomProvider = RoomProvider.Base()
        val booksCacheDataSource = BooksCacheDataSource.Base(roomProvider,booksCacheMapper)

        val booksCommunication = BooksCommunication.Base()
        val booksRepository = BooksRepository.Base(
            booksCloudDataSource,
            booksCloudMapper,
            booksCacheDataSource,
            booksCacheMapper
        )
        val booksInteractor = BooksInteractor.Base(booksRepository, BaseBooksDataToDomainMapper())
        mainViewModel = MainViewModel(booksInteractor,
            BaseBooksDomainMapper(booksCommunication, ResourceProvider.Base(applicationContext)), booksCommunication
        )
    }
    }
