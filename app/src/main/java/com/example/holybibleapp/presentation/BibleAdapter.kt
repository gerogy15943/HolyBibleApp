package com.example.holybibleapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.holybibleapp.R

class BibleAdapter(private val tryAgainClick: TryAgainClick): RecyclerView.Adapter<BibleAdapter.BibleViewHolder>() {

    abstract class BibleViewHolder(view: View): RecyclerView.ViewHolder(view){
        open fun bind(book: BookUi){
        }

        class FullScreenProgress(view: View): BibleViewHolder(view)

        class Base(view: View): BibleViewHolder(view){
            private val book_name_tv = itemView.findViewById<TextView>(R.id.book_name_tv)
            override fun bind(book: BookUi) {
                book.map(object: BookUi.StringMapper{
                    override fun map(text: String) {
                        book_name_tv.text = text
                    }
                })
            }
        }

        class Fail(view: View, private val tryAgainClick: TryAgainClick): BibleViewHolder(view){
            private val fail_message_tv = itemView.findViewById<TextView>(R.id.fail_message_tv)
            private val fail_try_again_btn = itemView.findViewById<Button>(R.id.fail_try_again_btn)
            override fun bind(book: BookUi) {
                book.map(object: BookUi.StringMapper{
                    override fun map(text: String) {
                        fail_message_tv.text = text
                    }
                })
            fail_try_again_btn.setOnClickListener {
                tryAgainClick.click()
            }
            }
        }
    }

    fun update(newList: List<BookUi>){
        books.clear()
        books.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (books[position]){
            is BookUi.Base -> 0
            is BookUi.Fail -> 1
            is BookUi.Testament -> 2
            is BookUi.Progress -> 3
            else -> -1
        }
    }

    private val books = ArrayList<BookUi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BibleViewHolder {
        val viewHolder = when(viewType) {
            0 -> BibleViewHolder.Base(R.layout.book_layout.getView(parent))
            1 -> BibleViewHolder.Fail(R.layout.fail_fullscreen.getView(parent), tryAgainClick)
            2 -> BibleViewHolder.Base(R.layout.testament_layout.getView(parent))
            else -> BibleViewHolder.FullScreenProgress(R.layout.progress_fullscreen.getView(parent))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: BibleViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int {
        return books.size
    }

    interface TryAgainClick{
        fun click()
    }

    private fun Int.getView(parent: ViewGroup) = LayoutInflater.from(parent.context).inflate(this, parent, false)
}