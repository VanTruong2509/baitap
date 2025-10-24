package com.example.tuan4.model

data class Book(
    val id: Int,
    val title: String,
    var isBorrowed: Boolean = false
)
