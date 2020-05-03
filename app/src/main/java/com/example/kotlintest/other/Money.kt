package com.example.kotlintest.other

//TODO operator运算符重载
/**
 * (P-238 其他运算符对照表)
 */
class Money(val value: Int) {

    //注意 这个plus是重载，并不是随便写的
    operator fun plus(money: Money): Money {
        //将当前Money对象的value 和 参数传入的Money对象的value相加
        val sum = value + money.value
        //将结果传给一个新的Money对象，并将该对象返回
        return Money(sum)
    }

    operator fun plus(newValue: Int): Money {
        val sum = value + newValue
        return Money(sum)
    }

}