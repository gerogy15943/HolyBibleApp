package com.example.holybibleapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.holybibleapp.domain.books.BooksDomainToBooksUiMapper
import com.example.holybibleapp.domain.books.BooksInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val booksInteractor: BooksInteractor,
                    private val mapper: BooksDomainToBooksUiMapper,
                    private val communication: BooksCommunication,
                    private val uiDataCache: UiDataCache
): ViewModel() {
    fun fetchBooks() {
        communication.map(listOf(BookUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
        val result = booksInteractor.fetchBooks().map(mapper)
        val resultCache = result.cache(uiDataCache)
        withContext(Dispatchers.Main) {
            resultCache.map(communication)
        }
    }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<BookUi>>){
        communication.observe(owner, observer)
    }

    fun collapseOrExpanded(id: String){
        communication.map(uiDataCache.changeState(id))
    }

    fun saveCollapsedStates() {
       uiDataCache.saveState()
    }
}