package com.example.mobilesmoka_iot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.os.Handler
import android.view.View
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ControlingActivity : AppCompatActivity() {
    private lateinit var database: FirebaseDatabase
    private lateinit var buttonRef: DatabaseReference
    private val handler = Handler()
    private val BUTTON_VALUE_ON = 1
    private val BUTTON_VALUE_OFF = 0
    private lateinit var BtnMakan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_controling)

        database = FirebaseDatabase.getInstance()
        buttonRef = database.reference.child("Kontrol").child("tombol")

        // Control button makan sekarang
        BtnMakan = findViewById(R.id.BtnMakan)
        BtnMakan.setOnClickListener {
            updateDatabaseValue(BUTTON_VALUE_ON)
            handler.postDelayed({ resetDatabaseValue() }, 3000)
        }

        // Mengatur nilai awal di Database saat aplikasi pertama kali dijalankan
        updateDatabaseValue(BUTTON_VALUE_OFF)

        // Back ke home atau main activity
        val img_back = findViewById<ImageView>(R.id.img_back)
        img_back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Btn jadwal
        val BtnJadwal = findViewById<Button>(R.id.BtnJadwal)
        BtnJadwal.setOnClickListener {
            val intent = Intent(this, JadwalActivity::class.java)
            startActivity(intent)
        }
    }

    private fun updateDatabaseValue(value: Int) {
        buttonRef.setValue(value)
            .addOnSuccessListener {
                // Aksi sukses
            }
            .addOnFailureListener { e ->
                // Aksi gagal
            }
    }

    private fun resetDatabaseValue() {
        updateDatabaseValue(BUTTON_VALUE_OFF)
    }
}
