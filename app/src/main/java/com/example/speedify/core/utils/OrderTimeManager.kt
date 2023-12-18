package com.example.speedify.core.utils

object OrderTimeManager {
    var remainingTimeInMilliSeconds: Long = 1 * 60 * 1000 // 5 minutes
    var isTimerRunning = false
    var currentBengkelId: Int = 0

    fun resetTimer(newBengkelId: Int) {
        if (currentBengkelId != newBengkelId) {
            remainingTimeInMilliSeconds = 1 * 60 * 1000
            isTimerRunning = false
            currentBengkelId = newBengkelId
        }
    }
}