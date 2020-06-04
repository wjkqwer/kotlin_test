package com.example.kotlintest.jetpack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by WJK On 2020/6/2
 */
class JetViewModel(countReserved: Int) : ViewModel() {

//    var counter = countReserved

    //MutableLiveData是一种可变的LiveData: getValue()/setValue/postValue
//    val counter = MutableLiveData<Int>()
    val counter: LiveData<Int> get() = _counter

    //TODO LiveData: map(),switchMap() P549
    //不暴露给外部
    private val _counter = MutableLiveData<Int>()

    init {
        _counter.value = countReserved
    }

    fun plusOne() {
        val count = counter.value ?: 0
        _counter.value = count + 1
    }

    fun clear() {
        _counter.value = 0
    }

}