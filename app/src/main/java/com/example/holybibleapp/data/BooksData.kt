package com.example.holybibleapp.data

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.domain.BooksDomain

sealed class BooksData: Abstract.Object<BooksDomain, Abstract.Mapper.Empty>()
