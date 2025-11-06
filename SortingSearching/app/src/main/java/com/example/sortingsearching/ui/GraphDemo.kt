package com.example.sortingsearching.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.*
import androidx.compose.ui.graphics.Color

import com.example.sortingsearching.algorithms.searching.*

@Composable
fun GraphDemo(modifier: Modifier = Modifier) {
    val graph = remember { sampleGraph(6) }
    var visitedNodes by remember { mutableStateOf(listOf<String>()) }
    var algorithm by remember { mutableStateOf("DFS") }

    Column(
        modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Graph Search Demo", style = MaterialTheme.typography.h6)

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("DFS", "BFS", "A*").forEach {
                Button(
                    onClick = { algorithm = it },
                    colors = ButtonDefaults.buttonColors(
                        if (it == algorithm) Color.Gray else MaterialTheme.colors.primary
                    )
                ) { Text(it) }
            }
        }

        Button(onClick = {
            visitedNodes = emptyList()
            CoroutineScope(Dispatchers.Main).launch {
                when (algorithm) {
                    "DFS" -> dfs(graph, "0") { n -> visitedNodes = visitedNodes + n; delay(400) }
                    "BFS" -> bfs(graph, "0") { n -> visitedNodes = visitedNodes + n; delay(400) }
                    "A*" -> aStar(graph, "0", "5") { n -> visitedNodes = visitedNodes + n; delay(400) }
                }
            }
        }) { Text("Start Search") }

        Spacer(Modifier.height(16.dp))
        Text("Visited Order: ${visitedNodes.joinToString(" → ")}")
    }
}
