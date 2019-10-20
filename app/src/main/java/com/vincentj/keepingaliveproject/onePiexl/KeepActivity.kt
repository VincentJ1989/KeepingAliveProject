package com.vincentj.keepingaliveproject.onePiexl

import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity

class KeepActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window = window
        window.setGravity(Gravity.START or Gravity.TOP)
        val params = window.attributes
        // 设置宽高
        params.width = 1
        params.height = 1
        // 设置其实位置
        params.x = 0
        params.y = 0

        window.attributes = params

        KeepManager.setKeep(this)
    }
}
