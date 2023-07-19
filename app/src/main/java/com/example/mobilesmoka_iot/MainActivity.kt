package com.example.mobilesmoka_iot

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.mobilesmoka_iot.`object`.FirebaseUtils
import com.example.mobilesmoka_iot.`object`.FirebaseUtils.firebaseAuth
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {
    private lateinit var btnbacklogin: ImageView
    private lateinit var tvHalo: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val user: FirebaseUser? = FirebaseUtils.firebaseAuth.currentUser
        tvHalo = findViewById(R.id.TVHalo)
        if(user != null){
            tvHalo.setText("Halo, " + user.email!!.substring(0, user.email!!.indexOf("@")))
        }


        //button back ke login
        btnbacklogin = findViewById(R.id.logout)
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
        val history = findViewById<ImageView>(R.id.History)
        history .setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
        val Guide = findViewById<ImageView>(R.id.Guide)
        Guide .setOnClickListener {
            val intent = Intent(this, GuideActivity::class.java)
            startActivity(intent)
        }
        val Information = findViewById<ImageView>(R.id.Information)
        Information .setOnClickListener {
            val intent = Intent(this, InformationActivity::class.java)
            startActivity(intent)
        }

        // Initialize Firebase
        FirebaseApp.initializeApp(this)
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
        firebaseAuth.signOut()
        // Kode untuk logout di sini
    }

    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}