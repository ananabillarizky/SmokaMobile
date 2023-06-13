package com.example.mobilesmoka_iot

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import java.util.Calendar

class JadwalActivity : AppCompatActivity() {
    private lateinit var tvtime1 : TextView
    private lateinit var editButton : Button

    private lateinit var tvtime2 : TextView
    private lateinit var editButton2 : Button

    private lateinit var tvtime3 : TextView
    private lateinit var editButton3 : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jadwal)

        tvtime1  = findViewById(R.id.tvtime1)
        editButton = findViewById(R.id.editButton )


        editButton.setOnClickListener{
            val currentTime = Calendar.getInstance()
            val startHour = currentTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentTime.get(Calendar.MINUTE)

            TimePickerDialog(this,TimePickerDialog.OnTimeSetListener { view, hourofDay, minute ->
                tvtime1 .setText("$hourofDay : $minute")
            }, startHour,startMinute, true).show()
        }

        tvtime2  = findViewById(R.id.tvtime2)
        editButton2 = findViewById(R.id.editButton2 )

        editButton2.setOnClickListener{
            val currentTime = Calendar.getInstance()
            val startHour = currentTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentTime.get(Calendar.MINUTE)

            TimePickerDialog(this,TimePickerDialog.OnTimeSetListener { view, hourofDay, minute ->
                tvtime2 .setText("$hourofDay : $minute")
            }, startHour,startMinute, true).show()
        }

        tvtime3  = findViewById(R.id.tvtime3)
        editButton3 = findViewById(R.id.editButton3 )
        editButton3.setOnClickListener{
            val currentTime = Calendar.getInstance()
            val startHour = currentTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentTime.get(Calendar.MINUTE)

            TimePickerDialog(this,TimePickerDialog.OnTimeSetListener { view, hourofDay, minute ->
                tvtime3 .setText("$hourofDay : $minute")
            }, startHour,startMinute, true).show()
        }

        val img_back = findViewById<ImageView>(R.id.img_back)
        img_back.setOnClickListener {
            val intent = Intent(this, ControlingActivity::class.java)
            startActivity(intent)
        }
    }
}