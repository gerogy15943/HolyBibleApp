package com.example.holybibleapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.holybibleapp.core.Abstract

interface BooksCommunication: Abstract.Mapper{
    fun map(list: List<BookUi>)

    fun observe(lifecycleOwner: LifecycleOwner, observer: Observer<List<BookUi>>)

    class Base: BooksCommunication{
        private val listLiveData = MutableLiveData<List<BookUi>>()

        override fun map(list: List<BookUi>) {
            listLiveData.value = list
        }

        override fun observe(lifecycleOwner: LifecycleOwner, observer: Observer<List<BookUi>>) {
            listLiveData.observe(lifecycleOwner, observer)
        }
    }
}
