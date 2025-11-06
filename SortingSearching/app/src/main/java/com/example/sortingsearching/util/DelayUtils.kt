package com.example.sortingsearching.util

import kotlinx.coroutines.delay

suspend fun delayIfNeeded(ms: Long) {
    if (ms > 0) delay(ms)
}
