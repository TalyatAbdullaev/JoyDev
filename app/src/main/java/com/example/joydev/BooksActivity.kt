package com.example.joydev

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.joydev.databinding.ActivityBooksBinding
import com.example.joydev.pojo.Book
import com.example.joydev.viewmodel.BooksViewModel
import com.example.joydev.viewmodel.BooksViewModelFactory

private lateinit var binding: ActivityBooksBinding
private lateinit var viewModel: BooksViewModel
private var page: Int = 1
private val books: ArrayList<Book> = arrayListOf()
private lateinit var pageSettings: SharedPreferences

class BooksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBooksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pageSettings = getSharedPreferences("page", Context.MODE_PRIVATE)
        page = pageSettings.getInt("page", 1)

        Log.d("PAGE", "page - " + page)

        viewModel = ViewModelProvider(
            this,
            BooksViewModelFactory(application)
        ).get(BooksViewModel::class.java)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        val adapter = BooksAdapter(books)
        recyclerView.adapter = adapter

        if (books.isEmpty()) {
            viewModel.loadBooks(page)
        }

        viewModel.books.observe(this) {
            books.clear()
            books.addAll(it)
            adapter.notifyDataSetChanged()
        }

        adapter.setOnReachEndListener(object : BooksAdapter.OnReachEndListener {
            override fun onReachEnd() {
                page++
                viewModel.loadBooks(page)

                Log.d("PAGE", "page - " + page)
                Log.d("TAG", "size - " + books.size)
            }
        })

        binding.btnDeleteBooks.setOnClickListener {
            viewModel.deleteAllBooksFromDB()
            books.clear()
            page = 1
            viewModel.loadBooks(page)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onPause() {
        super.onPause()
        pageSettings.edit().putInt("page", page).apply()
    }
}