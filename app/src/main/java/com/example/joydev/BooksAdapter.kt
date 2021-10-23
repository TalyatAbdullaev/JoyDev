package com.example.joydev

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.joydev.databinding.BookItemBinding
import com.example.joydev.pojo.Book

class BooksAdapter(private val books: List<Book>) :
    RecyclerView.Adapter<BooksAdapter.BookViewHolder>() {
    private var onReachEndListener: OnReachEndListener? = null

    fun setOnReachEndListener(onReachEndListener: OnReachEndListener) {
        this.onReachEndListener = onReachEndListener
    }

    inner class BookViewHolder(val binding: BookItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        with(holder) {
            with(books[position]) {
                binding.tvAuthor.text = this.author
                binding.tvTitle.text = this.title
                binding.tvPublicationDate.text = TimeUtils().convertTime(this.publicationDate)

            }
        }

        Log.d("TAG", "positionadapter" + position)
        Log.d("TAG", "sizeadapter" + books.size)

        if (books.isNotEmpty() && position == books.size - 5 && onReachEndListener != null) {
            onReachEndListener!!.onReachEnd()
        }
    }

    override fun getItemCount(): Int = books.size

    interface OnReachEndListener {
        fun onReachEnd()
    }
}