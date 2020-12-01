package com.example.kotlintest.other

import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * Created by WJK On 2020/4/19
 */

fun main() {
    val learn = LearnKotlin()
//    learn.main1()
//    learn.main2()
    learn.main3()
}

class LearnKotlin {

    fun main1() {
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

//    foreachTest()
//    highFunTest()

//    letAndAlsoTest()
    }

    fun doStudy(study: Study) {
        study.readBooks()
        study.doHomework()
    }

//TODO if
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

//TODO when
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

    //TODO 区间
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

    //TODO list、map各种写法
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

        val map3 = mapOf("aaa" to "w", "bbb" to 2, "ccc" to 1)
        val map4 = mutableMapOf("aaa" to "w", "bbb" to 2, "ccc" to 1)
        map4["ddd"] = 3

        for ((str, num) in map3) {
            println("$str ---- $num")
        }
    }

    //TODO foreach
    fun foreachTest() {
        val list = listOf(1, 2, 3, 4, 5, 6)
        list.forEach {
            println("start value = $it")
            if (it == 2) return@forEach  //这个return@forEach意思是当2的时候，不往下执行，并不是跳出循环，类型java的continue
            if (it == 5) return     //跳出循环
            println("end value = $it")
        }
    }

    //TODO filter/any/all/asSequence()
    fun highFunTest() {
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

        println("------asSequence")

        //使用asSequence() 和不使用asSequence() 的循环遍历规则是不一样的。不使用会比使用次数多
        println("--使用asSequence()")
        val list1 = listOf(1, 2, 3, 4, 5, 6)
        println(list1.asSequence().filter {
            println("filter: $it")
            it > 3
        }.map {
            println("map: $it")
            it * 2
        }.forEach(::println))

        println("--不使用asSequence()")
        println(list1.filter {
            println("filter: $it")
            it > 3
        }.map {
            println("map: $it")
            it * 2
        }.forEach(::println))

        println("------flatMap")
        //flatMap原理去网上查
        val flatMapResult = list1.flatMap {
            0 until it      //until 左闭右开 区间
        }
        println(flatMapResult)

        //还有sum,reduce,fold等
    }


    fun lambdaTest2() {
        Thread(object : Runnable {
            override fun run() {
                "Not yet implemented"
            }
        }).start()

        Thread(Runnable { "Not yet implemented" }).start()
        Thread { "Not yet implemented" }.start()
    }

    //TODO ?/?./?:/!!
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

    //TODO let/also
    fun letAndAlsoTest() {
        val person = Person("wjk", 18)
        println()

        //also是返回调用者自己，与最后一行无关
        println("also:" + person.also {
            println("I'm also")
            2
        })

        //let是返回最后一行
        println("let:" + person.let {
            println("I'm let")
            3
        })
    }

    fun main2() {
//    paramTest(1, "aaa")
//    paramTest(2)
//    paramTest(str = "bbb", num = 3)

        //标准函数
//    withTest()
//    runTest()
//    applyTest()

        //顶层方法
//    doSomething()

        kuoZhanTest()
        operatorTest()
    }

    fun paramTest(num: Int, str: String = "abc") {
        println("$num----$str")
    }

    //TODO   with接收两个参数，一个作为上下文，一个lambda，并返回最后一行
    fun withTest() {
        val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
        val builder = StringBuilder()
        builder.append("Start eating fruits.\n")
        for (fruit in list) {
            builder.append(fruit).append("\n")
        }
        builder.append("Ate all fruits.")
        val result = builder.toString()
        println(result)
        println()

        //使用with: 把StringBuilder传到lambda表达式中作为上下文，并返回最后一行
        val withResult = with(StringBuilder()) {
            append("Start eating fruits.\n")
            for (fruit in list) {
                append(fruit).append("\n")
            }
            append("Ate all fruits.")
            toString()
        }
        println(withResult)
        println()
    }


    //TODO run函数不能直接调用。接收一个lambda参数，并返回最后一行
    fun runTest() {
        val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
        val result = StringBuilder().run {
            append("Start eating fruits.\n")
            for (fruit in list) {
                append(fruit).append("\n")
            }
            append("Ate all fruits.")
            toString()
        }
        println(result)
        println()
    }


    //TODO apply和run相似，只是没有返回值，而且会自动返回调用对象本身
    fun applyTest() {
        val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
        val result = StringBuilder().apply {
            append("Start eating fruits.\n")
            for (fruit in list) {
                append(fruit).append("\n")
            }
            append("Ate all fruits.")
        }
        println(result.toString())
        println()
    }

//TODO 静态方法
    /**
     * 这样就可以用Util.staticFun1()来调用，不过这样写法会将整个类中所有方法都变成类似于静态方法的调用方式，但其实它并不是静态方法
     */
    object Util {
        fun staticFun1() {
            println("this is static")
        }
    }

    /**
     * 这样就可以用Util1.staticFun2()来调用，但其实它并不是静态方法
     */
    class Util1 {
        companion object {
            fun staticFun2() {
                println("this is static2")
            }
        }
    }

    /**
     * 真正做到静态方法
     * 1、注解 @JvmStatic :只能加在 单例类 或者 companion object中的方法上
     * 2、顶层方法 Test.kt中的 doSomething()
     */
    class Util2 {
        companion object {
            @JvmStatic
            fun staticFun3() {
                println("this is static2")
            }
        }
    }


    //TODO 使用 lateinit 延迟加载，告诉编译器，会在晚些时候对这个变量进行初始化，就不用一开始将他赋值为null了
    private var student: Student? = null
    private lateinit var student1: Student

    fun lateinitTest() {
        student = Student()
        student?.doHomework()

        //可使用 ::student1.isInitialized 判断是否已经初始化
        if (!::student1.isInitialized) {
            student1 = Student()
        }
        //此时也不用在使用?进行判空处理了
        student1.doHomework()
    }

    //TODO 扩展函数测试
    fun kuoZhanTest() {
        val count = "ABC123xyz!@#".letterCount()
        println(count)
        val str = "abc".times(3)
        println(str)
    }

    //TODO operator运算符重载测试
    fun operatorTest() {
        val money1 = Money(4)
        val money2 = Money(10)
        val money3 = money1 + money2
        val money4 = money3 + 20
        println(money3.value)
        println(money4.value)
    }


    fun main3() {
        this add "abc"   // 正确
        add("abc")       // 正确
//        add "abc"        // 错误：必须指定接收者

        val list= listOf("aa","bb","cc")
        if(list has "aa"){
            println("has aa")
        }

        println(lazyValue)
        println(lazyValue)
    }

    //TODO infix 中缀表示法，标有 infix 关键字的函数也可以使用中缀表示法（忽略该调用的点与圆括号）调用。
    /**
     * 它们必须是成员函数或扩展函数，不能是顶层函数；
     * 它们必须只有一个参数；
     * 其参数不得接受可变数量的参数且不能有默认值。
     * 中缀函数调用的优先级低于算术操作符、类型转换以及 rangeTo 操作符。
     * 另一方面，中缀函数调用的优先级高于布尔操作符 && 与 ||、is- 与 in- 检测以及其他一些操作符。
     */
    infix fun add(str: String) {
        println(str)
    }

    infix fun <T> Collection<T>.has(element: T) = contains(element)


    //TODO  :: 双冒号,不是一个函数，而是一个对象，一个函数类型的对象
    /**
     * 双冒号加上函数名的这个写法，它是一个指向对象的引用，但并不是指向函数本身，
     * 而是指向一个我们在代码里看不见的对象。这个对象复制了原函数的功能，但它并不是原函数。
     */
    fun b(param: Int): String {
        return param.toString()
    }
    val d = ::b
    //d 已经是个对象了，所以直接写就行了
    val e = d


    //kotlin中匿名函数不——是——函——数。它是个对象，一个函数类型的对象。所以，才可以直接把它当做函数的参数来传递以及赋值给变量。
    //同理，Lambda 其实也是一个函数类型的对象而已。你能怎么使用双冒号加函数名，就能怎么使用匿名函数，以及怎么使用 Lambda 表达式。
    //在你知道了在 Kotlin 里「函数并不能传递，传递的是对象」和「匿名函数和 Lambda 表达式其实都是对象」这些本质之后，你以后去写 Kotlin 的高阶函数会非常轻松非常舒畅。

    //TODO lateinit var和by lazy
    //lateinit var只是让编译期忽略对属性未初始化的检查，后续在哪里以及何时初始化还需要开发者自己决定。
    //by lazy真正做到了声明的同时也指定了延迟初始化时的行为，在属性被第一次被使用的时候能自动初始化。

    val lazyValue: String by lazy {
        println("computed!")
        "Hello"
    }

}













