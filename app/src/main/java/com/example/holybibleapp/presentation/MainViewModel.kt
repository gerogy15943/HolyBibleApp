package com.example.holybibleapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.Book
import com.example.holybibleapp.domain.BooksDomainToBooksUiMapper
import com.example.holybibleapp.domain.BooksInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val booksInteractor: BooksInteractor,
                    private val mapper: BooksDomainToBooksUiMapper,
                    private val communication: BooksCommunication
): ViewModel() {
    fun fetchBooks() = viewModelScope.launch(Dispatchers.IO) {
        val result = booksInteractor.fetchBooks().map(mapper)
        with(Dispatchers.Main) {
            result.map(Abstract.Mapper.Empty)
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<Book>>){
        communication.observeSuccess(owner, observer)
    }
}