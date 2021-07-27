package com.example.task7.workmanager

import android.content.Context
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class WorkManager(context: Context) {
    val manager = WorkManager.getInstance(context).enqueue(
        PeriodicWorkRequest.Builder(TestWork::class.java, 1, TimeUnit.MINUTES).build()
    )
}