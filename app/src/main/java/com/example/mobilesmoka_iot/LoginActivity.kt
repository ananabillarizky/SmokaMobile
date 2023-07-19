package com.example.mobilesmoka_iot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.mobilesmoka_iot.`object`.Extension.toast
import com.example.mobilesmoka_iot.`object`.FirebaseUtils.firebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlin.math.log


class LoginActivity : AppCompatActivity() {

    lateinit var signInEmail: String
    lateinit var signInPassword: String
    lateinit var signInInputsArray: Array<EditText>
    private lateinit var emailEdt: EditText
    private lateinit var passwordEdt: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        emailEdt = findViewById(R.id.emailET)
        passwordEdt = findViewById(R.id.passwordET)
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
        var isPasswordVisible = false
        IV_eye.setOnClickListener {
            if (!isPasswordVisible) {
                // Mengubah tampilan password menjadi tersembunyi
                passwordET.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                IV_eye.setImageResource(R.drawable.ic_eye_off)
            } else {
                // Mengubah tampilan password menjadi terlihat
                passwordET.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                IV_eye.setImageResource(R.drawable.ic_eye)
            }
            isPasswordVisible = !isPasswordVisible

            // Mengatur kursor ke posisi akhir teks
            passwordET.setSelection(passwordET.text.length)
        }
        ///test masuk ke main activity
        val loginButton = findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        signInInputsArray = arrayOf(emailEdt, passwordEdt)
        tv_register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

        loginButton.setOnClickListener {
            signInUser()
        }


    }

    override fun onStart() {
        super.onStart()
        val user: FirebaseUser? = firebaseAuth.currentUser
        user?.let {
            startActivity(Intent(this, MainActivity::class.java))
            toast("Selamat Datang Kembali")
        }
    }

    private fun notEmpty(): Boolean = signInEmail.isNotEmpty() && signInPassword.isNotEmpty()

    private fun signInUser() {
        signInEmail = emailEdt.text.toString().trim()
        signInPassword = passwordEdt.text.toString().trim()

        if (notEmpty()) {
            firebaseAuth.signInWithEmailAndPassword(signInEmail, signInPassword)
                .addOnCompleteListener { signIn ->
                    if (signIn.isSuccessful) {
                        startActivity(Intent(this, MainActivity::class.java))
                        toast("Login Sukses")
                        finish()
                    } else {
                        toast("Login Gagal")
                    }
                }
        } else {
            signInInputsArray.forEach { input ->
                if (input.text.toString().trim().isEmpty()) {
                    input.error = "${input.hint} dibutuhkan"
                }
            }
        }
    }
}