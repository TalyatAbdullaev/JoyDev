package com.example.joydev.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.joydev.api.ApiFactory
import com.example.joydev.database.BooksDatabase
import com.example.joydev.pojo.Book
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class BooksViewModel(application: Application): AndroidViewModel(application) {
    private val booksDB: BooksDatabase = BooksDatabase.getInstance(application)
    val books: MutableLiveData<List<Book>> = MutableLiveData(getAllBooksFromDB())
    private val compositeDisposable = CompositeDisposable()

    fun loadBooks(page: Int) {
        compositeDisposable.add(ApiFactory.apiService.getBooks(page)
            .subscribeOn(Schedulers.io())
            .subscribe ( {
                booksDB.booksDao().insertBooks(it)
                books.postValue(getAllBooksFromDB())
                Log.d("TAG", "responce " + it.toString())
            }, {
                //Toast.makeText(getApplication(), "Отсутствует интернет подключение!", Toast.LENGTH_SHORT).show()
                Log.d("TAG", "error " + it.message.toString())
            } ))
    }

    fun getAllBooksFromDB(): List<Book> {
        return booksDB.booksDao().getAllBooks()
    }

    fun deleteAllBooksFromDB() {
        booksDB.booksDao().deleteAllBooks()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}