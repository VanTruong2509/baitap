package com.example.tuan4.data

import com.example.tuan4.model.Book
import com.example.tuan4.model.Student

object SampleData {
    val books = listOf(
        Book(1, "Lập trình Kotlin cơ bản"),
        Book(2, "Cấu trúc dữ liệu & Giải thuật"),
        Book(3, "Phát triển ứng dụng Android"),
        Book(4, "Trí tuệ nhân tạo nhập môn"),
        Book(5, "Doremon")
    )

    val students = listOf(
        // ✅ A mượn sách 1 và 3
        Student(
            id = 1,
            name = "Nguyen Van A",
            borrowedBooks = mutableListOf(1, 3)
        ),
        // ✅ B mượn sách 2
        Student(
            id = 2,
            name = "Nguyen Thi B",
            borrowedBooks = mutableListOf(2)
        ),
        // ✅ C chưa mượn gì
        Student(
            id = 3,
            name = "Nguyen Van C",
            borrowedBooks = mutableListOf()
        )
    )
}
