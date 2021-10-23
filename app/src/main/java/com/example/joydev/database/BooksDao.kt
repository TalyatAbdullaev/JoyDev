package com.example.joydev.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.joydev.pojo.Book

@Dao
interface BooksDao {

    @Query("SELECT * FROM books")
    fun getAllBooks(): List<Book>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBooks(books: List<Book>)

    @Query("DELETE FROM books")
    fun deleteAllBooks()
}