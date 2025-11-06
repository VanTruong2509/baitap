package com.example.sortingsearching.algorithms.sorting

suspend fun mergeSort(arr: MutableList<Int>, onStep: suspend (Int, Int) -> Unit) {
    suspend fun mergeSortRange(l: Int, r: Int) {
        if (l >= r) return
        val m = (l + r) / 2
        mergeSortRange(l, m)
        mergeSortRange(m + 1, r)
        val tmp = mutableListOf<Int>()
        var i = l
        var j = m + 1
        while (i <= m && j <= r) {
            if (arr[i] <= arr[j]) tmp.add(arr[i++]) else tmp.add(arr[j++])
        }
        while (i <= m) tmp.add(arr[i++])
        while (j <= r) tmp.add(arr[j++])
        for (k in tmp.indices) {
            arr[l + k] = tmp[k]
            onStep(l + k, l + k)
        }
    }
    mergeSortRange(0, arr.size - 1)
}
