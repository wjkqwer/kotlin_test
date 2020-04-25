package com.example.kotlintest.other

import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * Created by WJK On 2020/4/19
 */

fun main() {
    main2()

//    var a = 10;
//    a += 10;
//    println("Hello World=$a")
//    println(max(1, 2))
//    println(testIf(2, 3))
//    println(whenTest1("Jim"))
//    println(whenTest2(32.5))
//    println(whenTest3("int"))
//    forTest()

//    val person = Person();
//    person.name = "wjk"
//    person.age = 22
//    person.eat()

//    val student0 = Student("a123", 5, "wjk", 18);
//    val student1 = Student("jk", 28);
//    val student2 = Student();
//    doStudy(student1)

//    Singleton.singletonTest()

//    listSetMapTest()

//    lambdaTest()
}

fun doStudy(study: Study) {
    study.readBooks()
    study.doHomework()
}

/**
 * if语句的每一个条件中，最后一行可以直接作为返回值
 */
fun testIf(num1: Int, num2: Int): Int {
    val value = if (num1 > num2) {
        num1
    } else {
        num2
    }
    return value
}

/**
 * if语句 一级进化
 */
fun testIf1(num1: Int, num2: Int): Int {
    return if (num1 > num2) {
        num1
    } else {
        num2
    }
}

/**
 * if语句 二级进化
 */
fun testIf2(num1: Int, num2: Int) = if (num1 > num2) {
    num1
} else {
    num2
}

/**
 * if语句 三级进化
 */
fun testIf3(num1: Int, num2: Int) = if (num1 > num2) num1 else num2

/**
 * when 和if类似，也是每一个条件中，最后一行可以直接作为返回值的
 */
fun whenTest(name: String) = when (name) {
    "Tom" -> 86
    "Jim" -> 77
    "Jack" -> 97
    else -> 0
}

fun whenTest1(name: String) = when (name) {
    "Tom" -> {
        61 + 2
    }
    "Jim" -> {
        51 + 2
    }
    "Jack" -> 97
    else -> 0
}

/**
 * when类型匹配
 */
fun whenTest2(num: Number): String {
    return when (num) {
        is Int -> "int"
        is Double -> "double"
        else -> "none"
    }
}

/**
 * when 另一种
 */
fun whenTest3(name: String) = when {
    name == "int" -> "name is int"
    name.startsWith("i") -> "name include ii"
    name == "double" -> "name is double"
    else -> "name is none"
}


fun forTest() {
    //左闭右闭 区间
    val range0 = 0..10
    //左闭右开 区间
    val range1 = 0 until 10
    //降序左闭右闭 区间
    val range2 = 10 downTo 1;

    for (i in range2) {
        println(i)
    }
    println()
    //step 跳过
    for (i in range1 step 2) {
        println(i)
    }
}


fun listSetMapTest() {

    //-------------list-------------
    val list1 = ArrayList<String>()
    list1.add("aaa")
    list1.add("bbb")
    list1.add("ccc")
    //这种集合为不可变集合，无法在进行增删改
    val list2 = listOf("aaa", "bbb", "ccc")
    //这种集合为可变集合
    val list3 = mutableListOf("aaa", "bbb", "ccc")
    list3.add("ddd")

    //-------------set-------------
    //set集合底层是使用hash映射机制来存放数据的，因此集合中的元素无法保证有序
    val set1 = setOf("aaa", "bbb", "ccc")
    val set2 = mutableSetOf("aaa", "bbb", "ccc")
    set2.add("ddd")

    //-------------map-------------
    //map不推荐这种写法
    val map = HashMap<String, Int>()
    map.put("aaa", 1)
    map.put("bbb", 2)
    map.put("ccc", 3)
    //map推荐这种写法
    val map1 = HashMap<String, Int>()
    //添加
    map1["aaa"] = 1;
    map1["bbb"] = 2;
    map1["ccc"] = 3;
    //读取
    val number = map1["aaa"]

    //TODO 这个value的值怎么能有String 和 int
    val map3 = mapOf("aaa" to "w", "bbb" to 2, "ccc" to 1)
    val map4 = mutableMapOf("aaa" to "w", "bbb" to 2, "ccc" to 1)
    map4["ddd"] = 3

    for ((str, num) in map3) {
        println("$str ---- $num")
    }
}


fun lambdaTest() {
    val list = listOf("aaa", "bbbbb", "cccc", "ddd")
    val maxLengthStr = list.maxBy { it: String -> it.length }
    println(maxLengthStr)

    //map函数 按照需求对集合中的元素进行任意的映射转换
    val mapList = list.map { it.toUpperCase() }
    for (str in mapList) {
        println(str)
    }
    println()

    //filter函数 过滤集合中长度小于等于4的元素，并转成大写（注意整理先filter在map，会比先map后filter效率，因为先map要先把所有的都转成大写）
    val filterList = list.filter { it.length <= 4 }.map { it.toUpperCase() }
    for (str in filterList) {
        println(str)
    }
    println()

    //any函数 判断集合中是否至少存在一个元素满足指定条件
    val anyResult = list.any { it.length <= 3 }
    //all函数 判断集合中是否所有元素都满足指定条件
    val allResult = list.all { it.length <= 3 }
    println("any = $anyResult; all = $allResult")

}

fun lambdaTest2() {
    Thread(object : Runnable {
        override fun run() {
            TODO("Not yet implemented")
        }
    }).start()

    Thread(Runnable { TODO("Not yet implemented") }).start()
    Thread { TODO("Not yet implemented") }.start()
}

fun nullTest() {
    val a = "abc"

    //下面两个方式相同
    if (a != null) {
        a.toUpperCase()
    }

    a?.toUpperCase()
}

/**
 *  ?.  空判断
 *  ?:  类似三目运算符
 *  !!  非空断言
 */
fun nullTest(study: Study?) {
    //判断非空方式一
    if (study != null) {
        study.doHomework()
        study.readBooks()
    }

    // ? 判断非空方式二
    study?.doHomework()
    study?.readBooks()

    // 三目运算
    val a = 0
    val b = 1
    val c = a ?: b
}


fun letTest(study: Study?) {
    //let函数会将study对象本身作为参数传递到lambda表达式中
    study?.let {
        it.doHomework()
        it.doHomework()
    }
}


fun main2() {
    paramTest(1, "aaa")
    paramTest(2)
    paramTest(str = "bbb", num = 3)
}

fun paramTest(num: Int, str: String = "abc") {
    println("$num----$str")
}































