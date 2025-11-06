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
import com.example.sortingsearching.algorithms.sorting.*

@Composable
fun SortingDemo(modifier: Modifier = Modifier) {
    var arr by remember { mutableStateOf(MutableList(10) { (1..100).random() }) }
    var current by remember { mutableStateOf(-1 to -1) }
    var isRunning by remember { mutableStateOf(false) }
    var algorithm by remember { mutableStateOf("Insertion") }

    Column(
        modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Sorting Visualization", style = MaterialTheme.typography.h6)
        Spacer(Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("Insertion", "Merge", "Quick").forEach {
                Button(
                    onClick = { algorithm = it },
                    colors = ButtonDefaults.buttonColors(
                        if (it == algorithm) Color.Gray else MaterialTheme.colors.primary
                    )
                ) { Text(it) }
            }
        }

        Spacer(Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            arr.forEachIndexed { index, value ->
                val color = when (index) {
                    current.first, current.second -> Color.Yellow
                    else -> Color.Cyan
                }
                Box(
                    Modifier
                        .width(20.dp)
                        .height((value * 2).dp)
                        .background(color, RoundedCornerShape(4.dp))
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                arr = MutableList(10) { (1..100).random() }
                current = -1 to -1
            }) { Text("Reset") }

            Button(onClick = {
                if (!isRunning) {
                    isRunning = true
                    CoroutineScope(Dispatchers.Main).launch {
                        val list = arr.toMutableList()
                        when (algorithm) {
                            "Insertion" -> insertionSort(list) { i, j -> current = i to j; delay(100) }
                            "Merge" -> mergeSort(list) { i, j -> current = i to j; delay(100) }
                            "Quick" -> quickSort(list) { i, j -> current = i to j; delay(100) }
                        }
                        arr = list
                        isRunning = false
                    }
                }
            }) { Text("Start") }
        }
    }
}
