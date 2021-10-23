package com.example.joydev.pojo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "books")
data class Book(

    @SerializedName("isbn")
    @Expose
    @PrimaryKey
    val id: String,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("description")
    @Expose
    val description: String,

    @SerializedName("author")
    @Expose
    val author: String,

    @SerializedName("publicationDate")
    @Expose
    val publicationDate: String
)
