package com.example.kotlintest.jetpack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Created by WJK On 2020/6/6
 */

object Repository {
    fun getUser(userId: String): LiveData<User> {
        val liveData = MutableLiveData<User>()
        liveData.value = User(userId, userId, 0)
        return liveData
    }
}
