package com.example.kotlintest.jetpack

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * Created by WJK On 2020/6/6
 */
//TODO WorkManager
class SimpleWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    /**
     * doWork()不会在主线程中运行，可以执行耗时逻辑
     */
    override fun doWork(): Result {
        Log.d("SimpleWorker", "do work in SimpleWorker")
        return Result.success()
    }


}