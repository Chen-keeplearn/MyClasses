package com.al.my_classes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.al.my_classes.base.CustomTimer
import com.al.my_classes.base.CustomTimer.ICustomTimerCallback
import com.al.my_classes.base.NoFastClickListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val timer = CustomTimer(10, object : ICustomTimerCallback() {
            override fun onFinish() {
                tv.text = "完成"
            }

            override fun onTicker(currentTime: Int) {
                tv.text = "$currentTime 秒"
            }

        })

        tv.setOnClickListener(object : NoFastClickListener() {
            override fun onNoFastClick(v: View) {

            }
        })

    }
}