package com.al.my_classes.base

import android.os.Handler

/**
 * 自定义计时器类
 * 1.支持传入总的时间
 * 2.时时回调,倒计时到几秒
 * 3.每过 1s, 总的时间减 1s
 * 4.总时间倒计时为0,要回调此时的状态
 * 另外：所有的接口回调,都是观察者模式
 */
class CustomTimer(time: Int, callback: ICustomTimerCallback) : Runnable {

    //倒计时总时间：5s,6s
    private var totalTime = time

    //倒计时回调
    private val listener = callback

    //倒计时时间
    private var countDownTime = totalTime

    //用于倒计时 handler.postDelayed
    private val handler = Handler()

    //倒计时开关
    private var isRun: Boolean = false

    /**
     * 倒计时具体实现
     */
    override fun run() {
        if (isRun) {
            listener.onTicker(countDownTime)
            if (countDownTime == 0) {
                listener.onFinish()
            } else {
                countDownTime = totalTime--
                handler.postDelayed(this, 1 * 1000)
            }
        }
    }

    //开始计时
    fun start() {
        isRun = true
        handler.post(this)
    }

    /**
     * 取消计时,节省资源,防止内存泄漏,页面已经销毁,计时仍在继续
     */
    fun cancel() {
        isRun = false
        handler.removeCallbacks(this)
    }


    /**
     * 观察者接口回调,可以定义接口,这里定义抽象类,可以选择性复写方法
     * (IOC数据回调)
     */
    abstract class ICustomTimerCallback {

        //倒计时
        open fun onTicker(currentTime: Int) {

        }

        //倒计时完毕回调
        abstract fun onFinish()
    }
}