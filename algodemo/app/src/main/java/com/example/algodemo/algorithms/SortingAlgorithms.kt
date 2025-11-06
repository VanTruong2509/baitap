package com.example.algodemo.algorithms

object SortingAlgorithms {
    fun bubbleSort(list: List<Int>, ascending: Boolean = true): List<Int> {
        val result = list.toMutableList()
        for (i in 0 until result.size - 1) {
            for (j in 0 until result.size - i - 1) {
                val condition = if (ascending) result[j] > result[j + 1] else result[j] < result[j + 1]
                if (condition) {
                    val temp = result[j]
                    result[j] = result[j + 1]
                    result[j + 1] = temp
                }
            }
        }
        return result
    }
}
