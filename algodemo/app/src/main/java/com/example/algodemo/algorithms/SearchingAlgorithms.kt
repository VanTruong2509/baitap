package com.example.algodemo.algorithms

object SearchingAlgorithms {

    fun linearSearch(list: List<Int>, target: Int): Int? {
        for (i in list.indices) {
            if (list[i] == target) return i
        }
        return null
    }

    fun binarySearch(list: List<Int>, target: Int): Int? {
        var left = 0
        var right = list.size - 1
        while (left <= right) {
            val mid = (left + right) / 2
            when {
                list[mid] == target -> return mid
                list[mid] < target -> left = mid + 1
                else -> right = mid - 1
            }
        }
        return null
    }
}
