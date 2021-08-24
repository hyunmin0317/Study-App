package com.example.myapplication.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_timer.*


class TimerActivity : AppCompatActivity() {
    // 핸들러사용
    val handler = Handler()
    var timeAll = 0
    var timeValue = 0
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)
        btn_stop.visibility = View.GONE


        //핸들러 - 1초마다 실행되게 함
        val runnable = object : Runnable {
            override fun run() {
                timeValue ++
                //TextView 업데이트 하기
                timeToText(timeValue)?.let {
                    time.text = it
                }
                handler.postDelayed(this, 1000)
            }
        }

        btn_start.setOnClickListener {
            it.visibility = View.GONE
            btn_stop.visibility = View.VISIBLE
            handler.post(runnable)
        }

        btn_stop.setOnClickListener {
            it.visibility = View.GONE
            btn_start.visibility = View.VISIBLE
            count++
            recordView.setText(recordView.text.toString()+ count.toString()+"회차: " + time.text.toString() + "\n")
            time.text = ""
            timeAll += timeValue
            timeValue = 0
            handler.removeCallbacks(runnable)
            timeToText()?.let {
                time.text = it
            }
            all_time.text = timeToText(timeAll)
        }
    }

    private fun timeToText(time: Int = 0) : String?{
        return if (time < 0) {
            null
        } else if (time == 0) {
            "00:00:00"
        } else {
            val h = time / 3600
            val m = time % 3600 / 60
            val s = time % 60
            "%1$02d:%2$02d:%3$02d".format(h, m, s)
        }
    }
}