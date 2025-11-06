package com.example.algodemo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SortingScreen(onBack: () -> Unit) {
    var numbers by remember { mutableStateOf(listOf(8, 3, 5, 1, 9, 2, 7,10,10000,99,10893,3847,38746,98474,48477,4844847,484848,4994857,765)) }
    var ascending by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("⚙️ Thuật toán Sắp xếp", fontSize = 22.sp)
        Spacer(Modifier.height(16.dp))

        Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("⬅️ Quay lại Menu chính")
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                ascending = !ascending
                numbers = if (ascending) numbers.sorted() else numbers.sortedDescending()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (ascending) "⬆️ Sắp xếp Tăng dần" else "⬇️ Sắp xếp Giảm dần")
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn {
            items(numbers) { num ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Text(
                        text = "Phần tử: $num",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }
    }
}
