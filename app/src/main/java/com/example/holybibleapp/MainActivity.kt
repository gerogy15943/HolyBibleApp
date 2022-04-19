package com.example.holybibleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.holybibleapp.core.BibleApp
import com.example.holybibleapp.presentation.BibleAdapter
import com.example.holybibleapp.presentation.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = (application as BibleApp).mainViewModel

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        val adapter = BibleAdapter(object: BibleAdapter.TryAgainClick{
            override fun click() {
                viewModel.fetchBooks()
            }
        }, object: BibleAdapter.CollapseListener{
            override fun collapseOrExpanded(id: String) {
                viewModel.collapseOrExpanded(id)
            }

        })
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        viewModel.observe(this, Observer {
            adapter.update(it)
        })
        viewModel.fetchBooks()
    }

    override fun onPause() {
        viewModel.saveCollapsedStates()
        super.onPause()
    }
}