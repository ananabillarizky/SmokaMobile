package com.example.mobilesmoka_iot

import FirebaseAuthHelper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
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

        createAccountInputsArray = arrayOf(emailEdt, passwordEdt, passwordConfirmEdt )
        btnRegister.setOnClickListener {
            signIn()
        }

       btnSignIn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            toast("Please sign into your account")
            finish()
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
                    input.error = "${input.hint} is required"
                }
            }
        } else {
            toast("passwords are not matching !")
        }
        return identical
    }

    private fun signIn() {
        if (identicalPassword()) {
            // identicalPassword() returns true only  when inputs are not empty and passwords are identical
            userEmail = emailEdt.text.toString().trim()
            userPassword = passwordEdt.text.toString().trim()

            /*create a user*/
            firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        toast("created account successfully !")
                        sendEmailVerification()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        toast("failed to Authenticate !")
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
                    toast("email sent to $userEmail")
                }
            }
        }
    }
}