package com.example.kotlintest

import android.app.Application
import android.content.Context

/**
 * Created by WJK On 2020/6/7
 */
class APP : Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}