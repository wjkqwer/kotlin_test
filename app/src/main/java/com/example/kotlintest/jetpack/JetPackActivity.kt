package com.example.kotlintest.jetpack

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlintest.R
import kotlinx.android.synthetic.main.activity_jet_pack.*

/**
 * Created by WJK On 2020/6/2
 */
class JetPackActivity : AppCompatActivity() {

    lateinit var viewModel: JetViewModel
    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jet_pack)
        sp = getPreferences(Context.MODE_PRIVATE)
        val counterReserved = sp.getInt("count_reserved", 0)
        lifecycle.addObserver(MyObserver())

        //绑定ViewModel
//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        //绑定可以传参的ViewModel
        viewModel = ViewModelProvider(
            this,
            JetViewModelFactory(counterReserved)
        ).get(JetViewModel::class.java)

        //观察数据变化
        viewModel.counter.observe(this, Observer { count ->
            infoText.text = count.toString()
        })

        plusOneBtn.setOnClickListener {
            viewModel.plusOne()
//            refreshCounter()
        }

        clearBtn.setOnClickListener {
            viewModel.clear()
//            refreshCounter()
        }

//        refreshCounter()

    }

    override fun onPause() {
        super.onPause()
//        sp.edit {
//            putInt("count_reserved", viewModel.counter)
//        }
        sp.edit().putInt("count_reserved", viewModel.counter.value ?: 0).apply()
    }

    private fun refreshCounter() {
        infoText.text = viewModel.counter.toString()
    }

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, JetPackActivity::class.java)
            context.startActivity(intent)
        }
    }
}
