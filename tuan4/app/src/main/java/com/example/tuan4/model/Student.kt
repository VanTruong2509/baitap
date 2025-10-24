package com.example.tuan4.model

data class Student(
    val id: Int,
    val name: String,
    val borrowedBooks: MutableList<Int> = mutableListOf()
)
