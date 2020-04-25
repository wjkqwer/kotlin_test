package com.example.kotlintest.other

/**
 * Created by WJK On 2020/4/19
 */
open class Person(val name: String, val age: Int) {

    //    var name = ""
//    var age = 0
    init {  //相当于java 构造函数中的步骤
        println("name is $name")
        println("age is $age")
    }

    fun eat() {
        println("$name is eating.He is $age years old")
    }

}