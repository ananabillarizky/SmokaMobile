package com.example.mobilesmoka_iot

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    private lateinit var btnbacklogin: ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //button back ke login
        btnbacklogin = findViewById(R.id.btnbacklogin)
        btnbacklogin.setOnClickListener {
            showLogoutConfirmationDialog()
        }


        //controling ke menu awal kontrol
        val controling = findViewById<ImageView>(R.id.controling)
        controling.setOnClickListener {
            val intent = Intent(this, ControlingActivity::class.java)
            startActivity(intent)
        }
        //btn monitoring ke menu moniroting
        val monitoring = findViewById<ImageView>(R.id.monitoring)
        monitoring.setOnClickListener {
            val intent = Intent(this, MonitoringActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Konfirmasi Logout")
        builder.setMessage("Apakah Anda yakin ingin keluar?")
        builder.setPositiveButton("Ya") { dialog, which ->
            // Kode untuk tindakan logout di sini
            // Misalnya, panggil metode logout() dan kembali ke layout login
            logout()
            goToLogin()
        }
        builder.setNegativeButton("Tidak", null)
        val dialog = builder.create()
        dialog.show()
    }

    private fun logout() {
        // Kode untuk logout di sini
    }

    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}