package com.example.holybibleapp.core

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.holybibleapp.domain.BooksDomainToBooksUiMapper
import com.example.holybibleapp.domain.BooksInteractor
import com.example.holybibleapp.presentation.BaseBooksDomainMapper
import com.example.holybibleapp.presentation.BooksCommunication
import com.example.holybibleapp.presentation.MainViewModel
import com.example.holybibleapp.presentation.ResourceProvider

class BibleApp: Application() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()

        private val booksInteractor: BooksInteractor = TODO()
        mainViewModel = MainViewModel(booksInteractor,
                        BaseBooksDomainMapper(BooksCommunication.Base(),ResourceProvider.Base(applicationContext)))
    }
}