package com.example.kotlintest.other

//TODO 泛型

//泛型类
class Generic<T> {
    fun method(param: T): T {
        return param
    }
}


class GenericMethod {
    //泛型方法
    fun <T> method(param: T): T {
        return param
    }

    //kotlin允许对泛型的类型进行限制，通过制定上界的方式
    //这样就只能传Int/Float/Double等
    fun <T : Number> methodNumber(param: T): T {
        return param
    }

    //在不手动指定上界时，泛型上界默认是 Any? 可为空，如果要让泛型的类型不可为空，需要手动指定成Any即可
}

fun main() {
    val generic = Generic<Int>()
    val result = generic.method(123)

    val genericMethod = GenericMethod()
    val result1 = genericMethod.method<Int>(123)
    val result2 = genericMethod.methodNumber(1.1)
}