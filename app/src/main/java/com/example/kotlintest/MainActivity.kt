package com.example.kotlintest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text_hello.text = "1122"
        text_hello.setOnClickListener{
            SecondActivity.actionStart(this,"123")
        }
    }

}
