package com.example.holybibleapp.data.books.net

import com.example.holybibleapp.core.Abstract
import com.example.holybibleapp.data.books.BookData
import com.google.gson.annotations.SerializedName


data class BookServerModel(
    @SerializedName("id")
    private val id: String,
    @SerializedName("name")
    private val name: String,
    @SerializedName("testament")
    private val testament: String
): Abstract.Object<BookData, BookServerToDataMapper> {
    override fun map(serverToDataMapper: BookServerToDataMapper): BookData {
        return serverToDataMapper.map(id, name, testament)
    }

}
