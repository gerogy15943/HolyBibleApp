package com.example.holybibleapp.data.net

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.core.Book
import com.google.gson.annotations.SerializedName


data class BookServerModel(
    @SerializedName("id")
    private val id: String,
    @SerializedName("name")
    private val name: String
): Abstract.Object<Book, BookServerToBookMapper>() {
    override fun map(mapper: BookServerToBookMapper): Book {
        return mapper.map(id, name)
    }

}
