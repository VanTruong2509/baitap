package com.example.tuan4.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.tuan4.data.SampleData
import com.example.tuan4.model.Book
import com.example.tuan4.model.Student

class LibraryViewModel : ViewModel() {

    var students = SampleData.students.toMutableList()
    var books = SampleData.books.toMutableList()

    var selectedStudent = mutableStateOf(students.first())

    fun changeStudent() {
        val currentIndex = students.indexOf(selectedStudent.value)
        val nextIndex = (currentIndex + 1) % students.size
        selectedStudent.value = students[nextIndex]
    }

    fun updateStudentName(newName: String) {
        val current = selectedStudent.value
        val updated = current.copy(name = newName)
        replaceStudent(updated)
    }

    fun toggleBook(book: Book) {
        val student = selectedStudent.value
        val updatedBorrowed = student.borrowedBooks.toMutableList()

        if (book.id in updatedBorrowed) {
            updatedBorrowed.remove(book.id)
        } else {
            updatedBorrowed.add(book.id)
        }

        val updatedStudent = student.copy(borrowedBooks = updatedBorrowed)
        replaceStudent(updatedStudent)
    }

    fun addBorrowedBooks(newBooks: List<Book>) {
        val student = selectedStudent.value
        val updatedBorrowed = student.borrowedBooks.toMutableList()

        newBooks.forEach { book ->
            if (book.id !in updatedBorrowed) {
                updatedBorrowed.add(book.id)
            }
        }

        val updatedStudent = student.copy(borrowedBooks = updatedBorrowed)
        replaceStudent(updatedStudent)
    }

    fun isBookBorrowed(book: Book): Boolean {
        return book.id in selectedStudent.value.borrowedBooks
    }

    private fun replaceStudent(updated: Student) {
        val index = students.indexOfFirst { it.id == updated.id }
        if (index != -1) {
            students[index] = updated
            selectedStudent.value = updated // ✅ Cập nhật lại Compose state
        }
    }
}
