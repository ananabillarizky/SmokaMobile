package com.example.mobilesmoka_iot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
//klik regis
        val tv_register = findViewById<TextView>(R.id.tv_register)
        tv_register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
//klik pass
        val tv_Fpassword = findViewById<TextView>(R.id.tv_Fpassword)
        tv_Fpassword.setOnClickListener {
            val intent = Intent(this, lupaPassActivity::class.java)
            startActivity(intent)
        }
//showpass
        val passwordET = findViewById<EditText>(R.id.passwordET)
        val IV_eye = findViewById<ImageView>(R.id.IV_eye)
        IV_eye.setOnClickListener {
            val currentInputType = passwordET.inputType
            if (currentInputType == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                passwordET.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                IV_eye.setImageResource(R.drawable.ic_eye_off)
            } else {
                passwordET.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                IV_eye.setImageResource(R.drawable.ic_eye)
            }
            passwordET.setSelection(passwordET.text.length)
        }
        ///test masuk ke main activity
        val loginButton = findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}