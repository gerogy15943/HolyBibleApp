package com.example.holybibleapp.core

import android.app.Application
import com.example.holybibleapp.data.BooksDataToDomain
import com.example.holybibleapp.domain.BooksInteractor
import com.example.holybibleapp.data.BooksRepository

class BibleApp: Application() {

    override fun onCreate() {
        super.onCreate()

        val booksRepository: BooksRepository = TODO()
        val booksInteractor = BooksInteractor.Base(booksRepository, BooksDataToDomain.Base())
    }
}