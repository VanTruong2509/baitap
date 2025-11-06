package com.example.sortingsearching.algorithms.sorting

suspend fun quickSort(arr: MutableList<Int>, onStep: suspend (Int, Int) -> Unit) {
    suspend fun partition(low: Int, high: Int): Int {
        val pivot = arr[high]
        var i = low - 1
        for (j in low until high) {
            if (arr[j] < pivot) {
                i++
                val tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp
                onStep(i, j)
            }
        }
        val tmp = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = tmp
        onStep(i + 1, high)
        return i + 1
    }
    suspend fun qs(low: Int, high: Int) {
        if (low < high) {
            val pi = partition(low, high)
            qs(low, pi - 1)
            qs(pi + 1, high)
        }
    }
    qs(0, arr.size - 1)
}
