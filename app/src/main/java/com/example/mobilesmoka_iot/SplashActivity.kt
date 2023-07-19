package com.example.mobilesmoka_iot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    //Variable timer splash screen
    private val SPLASH_TIME :Long= 3000 // delay 3 detik

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //login ke splashscreen setelah delay
        Handler().postDelayed( {
            startActivity(Intent(this,LoginActivity::class.java ))
            finish()
        }, SPLASH_TIME)
    }
}