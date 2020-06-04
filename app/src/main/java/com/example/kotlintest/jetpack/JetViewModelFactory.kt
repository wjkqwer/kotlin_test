package com.example.kotlintest.jetpack

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by WJK On 2020/6/2
 */
class JetViewModelFactory(private val countReserved: Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return JetViewModel(countReserved) as T
    }
}