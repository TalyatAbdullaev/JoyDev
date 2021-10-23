package com.example.joydev.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.joydev.pojo.Book

@Database(entities = [Book::class], version = 1, exportSchema = false)
abstract class BooksDatabase : RoomDatabase() {
    companion object {
        private val DB_NAME = "books_database"
        private var db: BooksDatabase? = null

        fun getInstance(context: Context): BooksDatabase {
            return db ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BooksDatabase::class.java,
                    DB_NAME
                )
                    .allowMainThreadQueries()
                    .build()
                db = instance
                // return instance
                instance
            }
        }
    }

    abstract fun booksDao(): BooksDao
}