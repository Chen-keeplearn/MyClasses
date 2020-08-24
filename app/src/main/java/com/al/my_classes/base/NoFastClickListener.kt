package com.al.my_classes.base

import android.view.View

/**
 * 按钮防快速重复点击
 * https://blog.csdn.net/zhufuing/article/details/53021835?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-3&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-3
 */
abstract class NoFastClickListener : View.OnClickListener {

    //两次点击的间隔时间
    private val minFastClickTime: Long = 600

    //最后点击的时间
    private var lastClickTime: Long = 0

    //对外露点击事件
    abstract fun onNoFastClick(v: View)

    //重写点击事件逻辑
    override fun onClick(v: View) {
        //当前点击时间
        val currentTime = System.currentTimeMillis()
        if ((currentTime - lastClickTime) >= minFastClickTime) {
            //将当前时间赋值最后一次点击时间
            lastClickTime = currentTime
            onNoFastClick(v)
        }
    }
}