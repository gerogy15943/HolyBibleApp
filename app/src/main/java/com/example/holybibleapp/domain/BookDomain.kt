package com.example.holybibleapp.domain

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.Book
import com.example.holybibleapp.data.net.BookServerModel
import com.example.holybibleapp.presentation.BookUi

sealed class BookDomain: Abstract.Object<BookUi, BookDomainToUi>(){

}
