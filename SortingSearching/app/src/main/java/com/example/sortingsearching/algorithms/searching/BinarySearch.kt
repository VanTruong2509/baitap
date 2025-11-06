package com.example.sortingsearching.algorithms.searching

suspend fun binarySearch(arr: List<Int>, target: Int, onStep: suspend (Int, Boolean) -> Unit) {
    var low = 0
    var high = arr.size - 1
    while (low <= high) {
        val mid = (low + high) / 2
        val found = arr[mid] == target
        onStep(mid, found)
        if (found) return
        if (arr[mid] < target) low = mid + 1 else high = mid - 1
    }
}
