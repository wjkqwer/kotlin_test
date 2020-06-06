package com.example.kotlintest.jetpack

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.kotlintest.R
import kotlinx.android.synthetic.main.activity_jet_pack.*
import java.util.concurrent.TimeUnit

/**
 * Created by WJK On 2020/6/2
 *
 * ViewModel: 用于存放与界面相关的数据，界面上能看得到的数据，它的相关变量都应该存放在ViewModel中
 *
 * Lifecycles:
 *
 * LiveData: 一种响应式编程组件，它可以包含任何类型的数据，并在数据发生变化的时候通知给观察者；
 *          map()：数据转换；
 *          switchMap()：
 *
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

        getUserBtn.setOnClickListener {
            val userId = (0..1000).random().toString()
            viewModel.getUser(userId)
        }
        viewModel.user.observe(this, Observer { user ->
            infoText.text = user.firstName
        })
//        refreshCounter()


        doWork.setOnClickListener {
            /**
             * 构建SimpleWorker
             * OneTimeWorkRequest用于构建单次的后台任务请求
             * PeriodicWorkRequest用于构建周期性的后台任务请求，但为了降低设备性能消耗，运行周期间隔不能短于15分钟
             */
            val request = OneTimeWorkRequest.Builder(SimpleWorker::class.java).build()
            val requestPeriodic = PeriodicWorkRequest.Builder(SimpleWorker::class.java,15,TimeUnit.MINUTES).build()
            //执行
            WorkManager.getInstance(this).enqueue(request)
            WorkManager.getInstance(this).enqueue(requestPeriodic)
        }

        val request = OneTimeWorkRequest.Builder(SimpleWorker::class.java)
            .setInitialDelay(5,TimeUnit.MINUTES)  //延迟执行
            .addTag("simple")   //添加标签，可以多个使用共同标签
            .setBackoffCriteria(BackoffPolicy.LINEAR,10,TimeUnit.MINUTES) //重新执行任务
            .build()

        //通过标签取消，可取消多个
        WorkManager.getInstance(this).cancelAllWorkByTag("simple")
        //通过id取消，只能取消单个
        WorkManager.getInstance(this).cancelWorkById(request.id)
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
