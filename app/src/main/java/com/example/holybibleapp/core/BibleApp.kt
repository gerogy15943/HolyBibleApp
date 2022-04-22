package com.example.holybibleapp.core

import android.app.Application
import com.example.holybibleapp.data.books.BooksRepository
import com.example.holybibleapp.data.books.net.BooksCloudMapper
import com.example.holybibleapp.data.books.cache.mappers.BookDbToDataMapper
import com.example.holybibleapp.data.books.cache.BooksCacheDataSource
import com.example.holybibleapp.data.books.cache.mappers.BookDataToDbMapper
import com.example.holybibleapp.data.books.cache.mappers.BooksCacheMapper
import com.example.holybibleapp.data.books.net.BookServerToDataMapper
import com.example.holybibleapp.data.books.net.BookService
import com.example.holybibleapp.data.books.net.BooksCloudDataSource
import com.example.holybibleapp.domain.books.BaseBookDataToDomainMapper
import com.example.holybibleapp.domain.books.BaseBooksDataToDomainMapper
import retrofit2.Retrofit
import com.example.holybibleapp.domain.books.BooksInteractor
import com.example.holybibleapp.presentation.*
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

        val bookDbToBookMapper = BookDbToDataMapper.Base()
        val bookDataToDbMapper = BookDataToDbMapper.Base()
        val booksCacheMapper = BooksCacheMapper.Base(bookDbToBookMapper, bookDataToDbMapper)
        val booksCloudDataSource = BooksCloudDataSource.Base(service)
        val bookServerToBookMapper = BookServerToDataMapper.Base()
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
        val resourceProvider = ResourceProvider.Base(applicationContext)
        val baseBookDomainToUiMapper = BaseBookDomainToUiMapper(resourceProvider)
        val baseBooksDomainToUiMapper = BaseBooksDomainToUiMapper(resourceProvider, baseBookDomainToUiMapper)
        val booksInteractor = BooksInteractor.Base(booksRepository, BaseBooksDataToDomainMapper(
            BaseBookDataToDomainMapper()
        )
        )
        val idCache = IdCache.Base(applicationContext)
        mainViewModel = MainViewModel(booksInteractor, baseBooksDomainToUiMapper, booksCommunication, UiDataCache.Base(idCache)
        )
    }
    }
