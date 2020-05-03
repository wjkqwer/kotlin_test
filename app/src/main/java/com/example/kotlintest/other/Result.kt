package com.example.kotlintest.other

//TODO 密封类
/**
 * 必须要else条件
 */
interface Result1
class Success1(val msg: String) : Result1
class Failure1(val error: Exception) : Result1

fun getResultMsg1(result: Result1) = when (result) {
    is Success1 -> result.msg
    is Failure1 -> result.error
    else -> throw IllegalArgumentException()
}


/**
 *  密封类 sealed class (P-204)
 *  密封类是一个可继承的类
 *  不在需要else条件，因为当when中传入一个密封类作为条件时，会自动检查该密封类有哪些子类，并强制要求实现所有子类的条件
 *  密封类只能定义在同一个文件的顶层位置，不能嵌套在其他类中
 */
sealed class Result
class Success(val msg: String) : Result()
class Failure(val error: Exception) : Result()

fun getResultMsg(result: Result) = when (result) {
    is Success -> result.msg
    is Failure -> result.error
}