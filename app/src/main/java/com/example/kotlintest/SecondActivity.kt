package com.example.kotlintest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }

    /**
     * 定义在这个companion object中方法都可以使用类似于JAVA静态方法的形式调用
     */
    companion object {
        fun actionStart(context: Context, data: String) {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra("data", data)
            context.startActivity(intent)
        }
    }
}
