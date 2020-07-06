package com.example.kotlintest.viewpager2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.kotlintest.R
import kotlinx.android.synthetic.main.activity_viewpager2.*

/**
 * ViewPager2的offScreenPageLimit默认值-1，不会预加载
 */
class Viewpager2Activity : AppCompatActivity() {

    private lateinit var viewPager2: ViewPager2
    private lateinit var myAdapter: MyAdapter
    private lateinit var mList: ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpager2)

        mList = ArrayList()
        for (index in 0 until 4) {
            mList.add(index)
        }

        initViewpager()
        viewPagerScroll.setOnClickListener { fakeDragBy(viewPager2) }
    }

    private fun initViewpager() {
        viewPager2 = findViewById(R.id.view_pager)
        myAdapter = MyAdapter()
        myAdapter.setList(mList)
        viewPager2.adapter = myAdapter

        //竖直滚动
//        viewpager.orientation = ViewPager2.ORIENTATION_VERTICAL

        //禁止滑动
//        viewPager2.isUserInputEnabled = false

        //通过ViewPager2的setPageTransformer方法来设置页面间距
        viewPager2.setPageTransformer(MarginPageTransformer(resources.getDimension(R.dimen.mDpDimen10).toInt()))

        //设置页面滚动动画
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(ScaleInTransformer())
        compositePageTransformer.addTransformer(MarginPageTransformer(resources.getDimension(R.dimen.mDpDimen10).toInt()))
        viewPager2.setPageTransformer(compositePageTransformer)

        //实现一屏多页
        viewPager2.apply {
            offscreenPageLimit=1
            val recyclerView= getChildAt(0) as RecyclerView
            recyclerView.apply {
                val padding = resources.getDimensionPixelOffset(R.dimen.mDpDimen10) +resources.getDimensionPixelOffset(R.dimen.mDpDimen10)
                // setting padding on inner RecyclerView puts overscroll effect in the right place
                setPadding(padding, 0, padding, 0)
                clipToPadding = false
            }
        }

        //监听
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            //内部的方法都是抽象方法，只需要实现需要的
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Toast.makeText(
                    this@Viewpager2Activity,
                    "page selected $position",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    //模拟手势滑动
    fun fakeDragBy(view: View) {
        viewPager2.beginFakeDrag()
        if (viewPager2.fakeDragBy(-310f))
            viewPager2.endFakeDrag()
    }

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, Viewpager2Activity::class.java)
            context.startActivity(intent)
        }
    }
}