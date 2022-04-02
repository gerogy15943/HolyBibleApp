package com.example.holybibleapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.holybibleapp.core.Book

interface BooksCommunication {
    fun show(list: List<Book>)
    fun show(message: String)

    fun observeSuccess(lifecycleOwner: LifecycleOwner, observer: Observer<List<Book>>)
    fun observeFail(lifecycleOwner: LifecycleOwner, observer: Observer<String>)

    class Base: BooksCommunication{
        private val successLiveData = MutableLiveData<List<Book>>()
        private val failLiveData = MutableLiveData<String>()

        override fun show(list: List<Book>) {
            successLiveData.value = list
        }

        override fun show(message: String) {
            failLiveData.value = message
        }

        override fun observeSuccess(
            lifecycleOwner: LifecycleOwner,
            observer: Observer<List<Book>>
        ) {
            successLiveData.observe(lifecycleOwner, observer)
        }

        override fun observeFail(lifecycleOwner: LifecycleOwner, observer: Observer<String>) {
            failLiveData.observe(lifecycleOwner, observer)
        }

    }
}
