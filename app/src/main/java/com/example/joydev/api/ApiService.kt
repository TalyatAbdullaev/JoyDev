package com.example.joydev.api

import com.example.joydev.pojo.Book
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @GET("books.json")
    fun getBooks(
        @Query("page") page: Int
    ): Single<List<Book>>
}