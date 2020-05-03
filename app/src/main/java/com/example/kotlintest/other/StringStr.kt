package com.example.kotlintest.other

//TODO 扩展函数
//扩展函数最好定义成顶层方法，这样可以让扩展函数拥有全局的访问域
/**
 * 相当于在String中添加了letterCount方法
 */
fun String.letterCount(): Int {
    var count = 0
    for (char in this) {
        if (char.isLetter()) {
            count++
        }
    }
    return count
}