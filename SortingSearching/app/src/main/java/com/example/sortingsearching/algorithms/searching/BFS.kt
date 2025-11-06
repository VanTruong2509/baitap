package com.example.sortingsearching.algorithms.searching

suspend fun bfs(graph: Map<String, List<String>>, start: String, onVisit: suspend (String) -> Unit) {
    val visited = mutableSetOf<String>()
    val queue = ArrayDeque<String>()
    queue.add(start)
    while (queue.isNotEmpty()) {
        val node = queue.removeFirst()
        if (node !in visited) {
            visited.add(node)
            onVisit(node)
            for (n in graph[node] ?: emptyList()) queue.add(n)
        }
    }
}
