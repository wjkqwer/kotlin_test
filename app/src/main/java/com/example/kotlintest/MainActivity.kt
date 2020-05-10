package com.example.kotlintest

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text_hello.text = "1122"
        text_hello.setOnClickListener {
            SecondActivity.actionStart(this, "123")
        }
    }


    //TODO 数据储存/读取方式
    /**
     * 文件储存,不用填写路径，默认储存到/data/data/<package name>/files/目录下
     */
    fun saveFile(inputText: String) {
        try {
            val output = openFileOutput("data", Context.MODE_PRIVATE)
            val write = BufferedWriter(OutputStreamWriter(output))
            //TODO use函数：保证在Lambda表达式中的代码全部执行完后，自动将外层的流关闭，就不用在finally中手动关闭了
            write.use {
                it.write(inputText)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * 文件读取
     */
    fun readFile(): String {
        val content = StringBuffer();
        try {
            val input = openFileInput("data")
            val reader = BufferedReader(InputStreamReader(input))
            reader.useLines {
                //TODO forEachLine函数：将读到的每行内容都回调到Lambda中
                reader.forEachLine {
                    content.append(it)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return content.toString()
    }

    /**
     * SharedPreferences
     */
    fun getPreferences() {
        //第一种获取方式
        getSharedPreferences("Name", Context.MODE_PRIVATE)
        //第二种获取方式，默认key为当前Activity的类名
        getPreferences(Context.MODE_PRIVATE)
    }

    fun usePreferences() {
        //存
        val editor = getSharedPreferences("main", Context.MODE_PRIVATE).edit()
        editor.putString("name", "wjk")
        editor.putInt("age", 22)
        editor.apply()

        //取
        val prefs = getSharedPreferences("main", Context.MODE_PRIVATE)
        println("name: ${prefs.getString("name", "")}")
        println("age: ${prefs.getInt("age", 0)}")


    }

    /**
     * SQLite
     */

}













