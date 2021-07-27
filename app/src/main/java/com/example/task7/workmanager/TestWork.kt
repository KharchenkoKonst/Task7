package com.example.task7.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class TestWork(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        try {
            Log.i("WM", "SuccessWork")
        } catch (e: Exception) {
            Log.e("WM", e.message.toString())
            return Result.failure()
        }
        return Result.success()
    }
}