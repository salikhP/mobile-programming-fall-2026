package com.example.homework02.model

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val pages: Int,
    val genre: String,
    val isBorrowed: Boolean = false
)
