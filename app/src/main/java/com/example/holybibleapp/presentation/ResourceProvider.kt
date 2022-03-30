package com.example.holybibleapp.presentation

import android.content.Context
import androidx.annotation.StringRes

interface ResourceProvider {
    fun getStringRes(@StringRes id: Int): String

    class Base(private val context: Context): ResourceProvider{
        override fun getStringRes(id: Int): String {
            return context.getString(id)
        }
    }
}