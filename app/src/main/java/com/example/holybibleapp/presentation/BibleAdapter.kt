package com.example.holybibleapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.holybibleapp.R
import com.example.holybibleapp.core.Book

class BibleAdapter: RecyclerView.Adapter<BibleAdapter.BibleViewHolder>() {

    inner class BibleViewHolder(view: View): RecyclerView.ViewHolder(){
        fun bind(book: Book){
            itemView.findViewById<TextView>(R.id.textView).text = book.name
        }
    }

    fun update(newList: List<Book>){
        books.clear()
        books.addAll(newList)
        notifyDataSetChanged()
    }


    private val books: ArrayList<Book>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BibleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_layout, parent, false)
        return BibleViewHolder(view)
    }

    override fun onBindViewHolder(holder: BibleViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int {
        return books.size
    }
}