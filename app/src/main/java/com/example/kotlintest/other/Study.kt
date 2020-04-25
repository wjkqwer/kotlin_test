package com.example.kotlintest.other

/**
 * Created by WJK On 2020/4/19
 */
interface Study {

    fun readBooks()

    /**
     * 当接口中的方法有了方法体，则实现类可选择是否重写该方法
     */
    fun doHomework() {
        println("I'm default")
    }

}