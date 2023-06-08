package com.example.mobilesmoka_iot.`object`

import android.app.Activity
import android.widget.Toast

object Extension {
    fun Activity.toast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}