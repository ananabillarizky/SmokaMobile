package com.example.mobilesmoka_iot

import FirebaseAuthHelper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import com.example.mobilesmoka_iot.`object`.Extension.toast
import com.example.mobilesmoka_iot.`object`.FirebaseUtils.firebaseAuth
import com.example.mobilesmoka_iot.`object`.FirebaseUtils.firebaseUser
import com.google.firebase.auth.FirebaseUser

class RegisterActivity : AppCompatActivity() {

    lateinit var userEmail: String
    lateinit var userPassword: String
    lateinit var createAccountInputsArray: Array<EditText>
    private lateinit var emailEdt: EditText
    private lateinit var passwordEdt: EditText
    private lateinit var passwordConfirmEdt: EditText
    private lateinit var btnRegister: Button
    private lateinit var btnSignIn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        emailEdt = findViewById(R.id.emailET)
        passwordEdt = findViewById(R.id.passwordET)
        passwordConfirmEdt = findViewById(R.id.ETconfrim)
        btnRegister = findViewById(R.id.btnregis)
        btnSignIn = findViewById(R.id.btn_to_signIn)

        createAccountInputsArray = arrayOf(emailEdt, passwordEdt, passwordConfirmEdt)
        btnRegister.setOnClickListener {
            signIn()
        }

        btnSignIn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            toast("Harap masuk ke akun anda!")
            finish()
        }
        val passwordET = findViewById<EditText>(R.id.passwordET)
        val IV_eye1 = findViewById<ImageView>(R.id.IV_eye1)
        var isPasswordVisible = false
        IV_eye1.setOnClickListener{
            if (!isPasswordVisible) {
                // Mengubah tampilan password menjadi tersembunyi
                passwordET.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                IV_eye1.setImageResource(R.drawable.ic_eye_off)
            } else {
                // Mengubah tampilan password menjadi terlihat
                passwordET.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                IV_eye1.setImageResource(R.drawable.ic_eye)
            }
            isPasswordVisible = !isPasswordVisible

            // Mengatur kursor ke posisi akhir teks
            passwordET.setSelection(passwordET.text.length)
        }

        val ETconfrim = findViewById<EditText>(R.id.ETconfrim)
        val IV_eye2 = findViewById<ImageView>(R.id.IV_eye2)
        var isPasswordVisible2 = false
        IV_eye2.setOnClickListener{
            if (!isPasswordVisible2) {
                // Mengubah tampilan password menjadi tersembunyi
                ETconfrim.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                IV_eye2.setImageResource(R.drawable.ic_eye_off)
            } else {
                // Mengubah tampilan password menjadi terlihat
                ETconfrim.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                IV_eye2.setImageResource(R.drawable.ic_eye)
            }
            isPasswordVisible2 = !isPasswordVisible2

            // Mengatur kursor ke posisi akhir teks
            ETconfrim.setSelection(ETconfrim.text.length)
        }

    }


    private fun notEmpty(): Boolean = emailEdt.text.toString().trim().isNotEmpty() &&
            passwordEdt.text.toString().trim().isNotEmpty() &&
            passwordConfirmEdt.text.toString().trim().isNotEmpty()

    private fun identicalPassword(): Boolean {
        var identical = false
        if (notEmpty() &&
            passwordEdt.text.toString().trim() == passwordConfirmEdt.text.toString().trim()
        ) {
            identical = true
        } else if (!notEmpty()) {
            createAccountInputsArray.forEach { input ->
                if (input.text.toString().trim().isEmpty()) {
                    input.error = "${input.hint} dibutuhkan"
                }
            }
        } else {
            toast("Password tidak sama!")
        }
        return identical
    }

    private fun signIn() {
        if (identicalPassword()) {
            // identicPassword() mengembalikan nilai true hanya jika input tidak kosong dan kata sandi identik
            userEmail = emailEdt.text.toString().trim()
            userPassword = passwordEdt.text.toString().trim()

            /*create a user*/
            firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        toast("akun berhasil dibuat!")
                        sendEmailVerification()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        var test = task.isSuccessful.toString()
                        toast(test)
                    }
                }
        }
    }


    /* send verification email to the new user. This will only
    *  work if the firebase user is not null.
    */

    private fun sendEmailVerification() {
        firebaseUser?.let {
            it.sendEmailVerification().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    toast("email terkirim ke $userEmail")
                }
            }
        }
    }
}