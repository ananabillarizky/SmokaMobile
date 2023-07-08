package com.example.mobilesmoka_iot

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.auth.FirebaseAuth

class lupaPassActivity : AppCompatActivity() {

    private lateinit var resetButton: Button
    private lateinit var emailET: EditText
    private lateinit var mAuth: FirebaseAuth
    private lateinit var backBtn:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        setContentView(R.layout.activity_lupa_pass)
        emailET = findViewById(R.id.emailET)
        resetButton = findViewById(R.id.resetButton)
        backBtn = findViewById(R.id.img_backlogin)

        resetButton.setOnClickListener {
            val email = emailET.text.toString().trim()

            if (email.isEmpty()) {
                emailET.error = "Email harus diisi!"
                emailET.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailET.error = "Masukan sesuai format email"
                emailET.requestFocus()
                return@setOnClickListener
            }
            val message: String? = "Apakah anda yakin ingin meresetnya?"
            showCustomDialogBox(message)
        }


        backBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showCustomDialogBox(message: String?) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.resetdialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val tvMessage1: TextView = dialog.findViewById(R.id.tvMessage1)
        val btnYes: Button = dialog.findViewById(R.id.btnYes)
        val btnNo: Button = dialog.findViewById(R.id.btnNo)

        tvMessage1.text = message

        btnYes.setOnClickListener {
            val email = emailET.text.toString().trim()
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this@lupaPassActivity,
                        "Cek email anda untuk mereset password",
                        Toast.LENGTH_LONG).show()
                    val intent = Intent(this@lupaPassActivity, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@lupaPassActivity, "Coba lagi! Ada kesalahan", Toast.LENGTH_LONG).show()
                }
            }
        }

        btnNo.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}


