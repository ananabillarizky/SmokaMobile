package com.example.mobilesmoka_iot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class ControlingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_controling)

        //back ke home atau main activity
        val img_back = findViewById<ImageView>(R.id.img_back)
        img_back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        //btn jadwal
        val BtnJadwal= findViewById<Button>(R.id.BtnJadwal)
        BtnJadwal.setOnClickListener {
            val intent = Intent(this, JadwalActivity::class.java)
            startActivity(intent)
        }
    }
}