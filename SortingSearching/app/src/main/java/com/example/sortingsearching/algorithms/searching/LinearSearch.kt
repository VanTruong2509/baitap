package com.example.sortingsearching.algorithms.searching

suspend fun linearSearch(arr: List<Int>, target: Int, onStep: suspend (Int, Boolean) -> Unit) {
    for (i in arr.indices) {
        val found = arr[i] == target
        onStep(i, found)
        if (found) return
    }
}
