package com.example.kotlintest.jetpack

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * Created by WJK On 2020/6/2
 */
//class MyObserver(val lifecycle:Lifecycle) : LifecycleObserver {
class MyObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun activityStart() {
//        Log.d("MyObserver", "activityStart")
        println("--------activityStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun activityStop() {
//        Log.d("MyObserver", "activityStop")
        println("--------activityStop")
    }
}