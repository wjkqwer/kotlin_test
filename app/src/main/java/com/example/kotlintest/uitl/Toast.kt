package com.example.kotlintest.uitl

import android.widget.Toast
import com.example.kotlintest.APP

/**
 * Created by WJK On 2020/6/7
 */

fun String.showToast(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(APP.context, this, duration).show()
}

fun Int.showToast(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(APP.context, this, duration).show()
}