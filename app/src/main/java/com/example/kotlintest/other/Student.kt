package com.example.kotlintest.other

/**
 * Created by WJK On 2020/4/19
 */
class Student(val sno: String, val grade: Int, name: String, age: Int) : Person(name, age),
    Study {

    init {  //相当于java 构造函数中的步骤
        println("sno is $sno")
        println("grade is $grade")
        println()
    }

    //次构造函数，this是调用主构造函数
    constructor(name: String, age: Int) : this("null", 0, name, age) {
        println("次构造函数")
    }

    //次构造函数，this是调用上面那个次构造函数
    constructor() : this("null", 0) {
        println("次次构造函数")
    }

    override fun readBooks() {
        println("$name is reading")
    }

//    override fun doHomework() {
//        println("$name is doing homework")
//    }
}