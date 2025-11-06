package com.example.sortingsearching.algorithms.sorting

suspend fun insertionSort(arr: MutableList<Int>, onStep: suspend (Int, Int) -> Unit) {
    for (i in 1 until arr.size) {
        val key = arr[i]
        var j = i - 1
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j]
            onStep(j + 1, j)
            j--
        }
        arr[j + 1] = key
        onStep(j + 1, i)
    }
}
