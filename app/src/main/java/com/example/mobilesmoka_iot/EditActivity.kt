package com.example.mobilesmoka_iot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        //back
        val img_back = findViewById<ImageView>(R.id.img_back)
        img_back.setOnClickListener {
            val intent = Intent(this, JadwalActivity::class.java)
            startActivity(intent)
        }
    }
}