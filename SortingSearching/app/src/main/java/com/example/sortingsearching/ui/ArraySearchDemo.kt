package com.example.sortingsearching.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.*
import com.example.sortingsearching.algorithms.searching.*

@Composable
fun ArraySearchDemo(modifier: Modifier = Modifier) {
    var input by remember { mutableStateOf("") }
    var arr by remember { mutableStateOf(listOf(2, 4, 5, 7, 9, 12, 15)) }
    var highlight by remember { mutableStateOf(-1) }
    var foundIndex by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Array Search Demo", style = MaterialTheme.typography.h6)
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("Nhập số cần tìm") }
        )

        Spacer(Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                val target = input.toIntOrNull() ?: return@Button
                CoroutineScope(Dispatchers.Main).launch {
                    foundIndex = null
                    linearSearch(arr, target) { i, found ->
                        highlight = i
                        if (found) foundIndex = i
                        delay(400)
                    }
                }
            }) { Text("Linear Search") }

            Button(onClick = {
                val target = input.toIntOrNull() ?: return@Button
                CoroutineScope(Dispatchers.Main).launch {
                    foundIndex = null
                    binarySearch(arr, target) { i, found ->
                        highlight = i
                        if (found) foundIndex = i
                        delay(400)
                    }
                }
            }) { Text("Binary Search") }
        }

        Spacer(Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            arr.forEachIndexed { index, value ->
                val color =
                    when {
                        index == highlight -> Color.Yellow
                        index == foundIndex -> Color.Green
                        else -> Color.Gray
                    }
                Box(
                    Modifier
                        .size(40.dp)
                        .background(color, RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) { Text("$value") }
            }
        }

        foundIndex?.let {
            Spacer(Modifier.height(12.dp))
            Text("✅ Found at index $it", color = Color.Green)
        }
    }
}
