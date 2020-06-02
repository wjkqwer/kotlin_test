package com.example.kotlintest.jetpack

import androidx.lifecycle.ViewModel

/**
 * Created by WJK On 2020/6/2
 */
class MainViewModel(countReserved: Int) : ViewModel() {

    var counter = countReserved

}