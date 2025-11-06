package com.example.sortingsearching.algorithms.searching

data class Node(val name: String, val g: Int, val h: Int) {
    val f: Int get() = g + h
}

suspend fun aStar(
    graph: Map<String, List<String>>,
    start: String,
    goal: String,
    onVisit: suspend (String) -> Unit
) {
    val heuristic = graph.keys.associateWith { it.toIntOrNull() ?: 0 } // demo heuristic
    val openList = mutableListOf(Node(start, 0, heuristic[start] ?: 0))
    val closedList = mutableSetOf<String>()

    while (openList.isNotEmpty()) {
        val current = openList.minByOrNull { it.f }!!
        onVisit(current.name)
        if (current.name == goal) return
        openList.remove(current)
        closedList.add(current.name)
        for (neighbor in graph[current.name] ?: emptyList()) {
            if (neighbor in closedList) continue
            val gScore = current.g + 1
            val hScore = heuristic[neighbor] ?: 0
            openList.add(Node(neighbor, gScore, hScore))
        }
    }
}
