package com.example.tuan4.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tuan4.model.Book
import com.example.tuan4.viewmodel.LibraryViewModel

@Composable
fun LibraryScreen(viewModel: LibraryViewModel) {
    val student = viewModel.selectedStudent.value
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Hệ thống Quản lý Thư viện",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Sinh viên")

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // ✅ KHÔNG nhớ theo `student`, để luôn hiển thị đúng khi đổi sinh viên
            TextField(
                value = student.name,
                onValueChange = { viewModel.updateStudentName(it) },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { viewModel.changeStudent() }) {
                Text("Thay đổi")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Danh sách sách đã mượn")

        val borrowedBooks = viewModel.books.filter { viewModel.isBookBorrowed(it) }

        if (borrowedBooks.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(8.dp)
            ) {
                items(borrowedBooks) { book ->
                    BookItem(
                        book = book,
                        checked = true,
                        onCheckedChange = { viewModel.toggleBook(book) }
                    )
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Sinh viên này chưa mượn sách nào." +
                        "Nhấn Thêm để mượn sách.")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { showDialog = true },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Thêm")
        }
    }

    if (showDialog) {
        AddBookDialog(
            viewModel = viewModel,
            onDismiss = { showDialog = false },
            onConfirm = { selectedBooks ->
                viewModel.addBorrowedBooks(selectedBooks)
                showDialog = false
            }
        )
    }
}

@Composable
fun BookItem(book: Book, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { onCheckedChange(it) }
            )
            Text(text = book.title)
        }
    }
}

@Composable
fun AddBookDialog(
    viewModel: LibraryViewModel,
    onDismiss: () -> Unit,
    onConfirm: (List<Book>) -> Unit
) {
    val student = viewModel.selectedStudent.value
    val availableBooks = viewModel.books.filter { it.id !in student.borrowedBooks }
    val selectedBooks = remember { mutableStateListOf<Book>() }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Chọn sách để mượn") },
        text = {
            if (availableBooks.isNotEmpty()) {
                LazyColumn {
                    items(availableBooks) { book ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                        ) {
                            Checkbox(
                                checked = selectedBooks.contains(book),
                                onCheckedChange = {
                                    if (it) selectedBooks.add(book)
                                    else selectedBooks.remove(book)
                                }
                            )
                            Text(text = book.title)
                        }
                    }
                }
            } else {
                Text("Không còn sách nào để mượn.")
            }
        },
        confirmButton = {
            TextButton(
                onClick = { onConfirm(selectedBooks) },
                enabled = selectedBooks.isNotEmpty()
            ) {
                Text("Xác nhận")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Hủy")
            }
        }
    )
}
