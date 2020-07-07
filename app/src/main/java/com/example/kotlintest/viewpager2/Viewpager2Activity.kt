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
 * 1.ViewPager2新特性
基于RecyclerView实现。这意味着RecyclerView的优点将会被ViewPager2所继承。
支持竖直滑动。只需要一个参数就可以改变滑动方向。
支持关闭用户输入。通过setUserInputEnabled来设置是否禁止用户滑动页面。
支持通过编程方式滚动。通过fakeDragBy(offsetPx)代码模拟用户滑动页面。
CompositePageTransformer 支持同时添加多个PageTransformer。
支持DiffUtil ，可以添加数据集合改变的item动画。
支持RTL (right-to-left)布局。我觉得这个功能对国内开发者来说可能用处不大..

2.相比ViewPager变化的API
ViewPager2相比ViewPager做了哪些改变呢？研究了一番之后我大概列出以下几点：

ViewPager2与ViewPager同是继承自ViewGrop，但是ViewPager2被声明成了final。意味着我们不可能再像ViewPager一样通过继承来修改ViewPager2的代码。
FragmentStatePagerAdapter被FragmentStateAdapter 替代
PagerAdapter被RecyclerView.Adapter替代
addPageChangeListener被registerOnPageChangeCallback。我们知道ViewPager的addPageChangeListener接收的是一个OnPageChangeListener的接口，而这个接口中有三个方法，当想要监听页面变化时需要重写这三个方法。而ViewPager2的registerOnPageChangeCallback方法接收的是一个叫OnPageChangeCallback的抽象类，因此我们可以选择性的重写需要的方法即可。
移除了setPargeMargin方法。
关于offScreenPageLimit--离屏加载新特性
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
        viewPager2.setPageTransformer(
            MarginPageTransformer(
                resources.getDimension(R.dimen.mDpDimen10).toInt()
            )
        )

        //设置页面滚动动画
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(ScaleInTransformer())
        compositePageTransformer.addTransformer(
            MarginPageTransformer(
                resources.getDimension(R.dimen.mDpDimen10).toInt()
            )
        )
        viewPager2.setPageTransformer(compositePageTransformer)

        //实现一屏多页
        viewPager2.apply {
            offscreenPageLimit = 1
            val recyclerView = getChildAt(0) as RecyclerView
            recyclerView.apply {
                val padding =
                    resources.getDimensionPixelOffset(R.dimen.mDpDimen10) + resources.getDimensionPixelOffset(
                        R.dimen.mDpDimen10
                    )
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