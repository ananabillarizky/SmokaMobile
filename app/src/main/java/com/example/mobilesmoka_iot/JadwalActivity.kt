package com.example.mobilesmoka_iot

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class JadwalActivity : AppCompatActivity() {
    private lateinit var tvtime1: TextView
    private lateinit var editButton: Button
    private lateinit var saveButton: Button

    private lateinit var tvtime2: TextView
    private lateinit var editButton2: Button
    private lateinit var saveButton2: Button

    private lateinit var tvtime3: TextView
    private lateinit var editButton3: Button
    private lateinit var saveButton3: Button

    private val database = FirebaseDatabase.getInstance()
    private val KontrolRef = database.reference.child("Kontrol")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jadwal)

        tvtime1 = findViewById(R.id.tvtime1)
        editButton = findViewById(R.id.editButton)
        saveButton = findViewById(R.id.saveButton)

        tvtime2 = findViewById(R.id.tvtime2)
        editButton2 = findViewById(R.id.editButton2)
        saveButton2 = findViewById(R.id.saveButton2)

        tvtime3 = findViewById(R.id.tvtime3)
        editButton3 = findViewById(R.id.editButton3)
        saveButton3 = findViewById(R.id.saveButton3)

        editButton.setOnClickListener {
            showTimePickerDialog(tvtime1)
        }

        saveButton.setOnClickListener {
            val timeValue = tvtime1.text.toString()
            saveDataToFirebase("waktu", timeValue)
        }

        editButton2.setOnClickListener {
            showTimePickerDialog(tvtime2)
        }

        saveButton2.setOnClickListener {
            val timeValue = tvtime2.text.toString()
            saveDataToFirebase("waktu2", timeValue)
        }

        editButton3.setOnClickListener {
            showTimePickerDialog(tvtime3)
        }

        saveButton3.setOnClickListener {
            val timeValue = tvtime3.text.toString()
            saveDataToFirebase("waktu3", timeValue)
        }

        val img_back = findViewById<ImageView>(R.id.img_back)
        img_back.setOnClickListener {
            val intent = Intent(this, ControlingActivity::class.java)
            startActivity(intent)
        }

        loadDataFromFirebase("waktu", tvtime1)
        loadDataFromFirebase("waktu2", tvtime2)
        loadDataFromFirebase("waktu3", tvtime3)
    }

    private fun showTimePickerDialog(textView: TextView) {
        val currentTime = Calendar.getInstance()
        val startHour = currentTime.get(Calendar.HOUR_OF_DAY)
        val startMinute = currentTime.get(Calendar.MINUTE)

        val isDarkMode = (textView.context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

        val themeResId = if (isDarkMode) R.style.TimePickerDialogStyle else R.style.TimePickerDialogStyle

        TimePickerDialog(
            textView.context,
            themeResId, // Use the custom style based on the current mode
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                val timeString = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute)
                textView.text = timeString
            }, startHour, startMinute, true
        ).show()
    }


    private fun saveDataToFirebase(childKey: String, timeValue: String) {
        val data = hashMapOf("time" to timeValue)
        KontrolRef.child(childKey)
            .setValue(data)
            .addOnSuccessListener {
                Toast.makeText(this, "Jadwal Pakan Berhasil Disimpan", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Gagal Menyimpan Jadwal Pakan", Toast.LENGTH_SHORT).show()
            }
    }

    private fun loadDataFromFirebase(childKey: String, textView: TextView) {
        KontrolRef.child(childKey)
            .get()
            .addOnSuccessListener { dataSnapshot ->
                val timeValue = dataSnapshot.child("time").getValue(String::class.java)
                timeValue?.let {
                    textView.text = it
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(
                    this,
                    "Gagal mengambil data dari Firebase: ${exception.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}