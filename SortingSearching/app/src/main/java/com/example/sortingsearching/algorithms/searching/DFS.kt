package com.example.sortingsearching.algorithms.searching

suspend fun dfs(graph: Map<String, List<String>>, start: String, onVisit: suspend (String) -> Unit) {
    val visited = mutableSetOf<String>()
    suspend fun visit(node: String) {
        if (node in visited) return
        visited.add(node)
        onVisit(node)
        for (n in graph[node] ?: emptyList()) visit(n)
    }
    visit(start)
}

fun sampleGraph(n: Int): Map<String, List<String>> {
    val map = mutableMapOf<String, MutableList<String>>()
    for (i in 0 until n) map[i.toString()] = mutableListOf()
    for (i in 0 until n) {
        val to = (1..3).map { (i + it) % n }.distinct()
        map[i.toString()]?.addAll(to.map { it.toString() })
    }
    return map
}
