package com.example.speedify.core.utils

object OrderTimeManager {
    var remainingTimeInMilliSeconds: Long = (5 * 60 + 5) * 1000  // 5 minutes and 5 seconds
    var isTimerRunning = false
    var currentBengkelId: Int = 0

    fun resetTimer(newBengkelId: Int) {
        if (currentBengkelId != newBengkelId) {
            remainingTimeInMilliSeconds = (5 * 60 + 5) * 1000
            isTimerRunning = false
            currentBengkelId = newBengkelId
        }
    }
}