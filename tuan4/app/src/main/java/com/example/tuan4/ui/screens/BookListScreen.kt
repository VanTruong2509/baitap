package com.example.tuan4.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tuan4.viewmodel.LibraryViewModel

@Composable
fun BookListScreen(viewModel: LibraryViewModel) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(viewModel.books) { book ->
            Text(text = "📘 ${book.title}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
