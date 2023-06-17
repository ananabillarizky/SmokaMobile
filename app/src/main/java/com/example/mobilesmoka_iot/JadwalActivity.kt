package com.example.mobilesmoka_iot

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar

class JadwalActivity : AppCompatActivity() {
    private lateinit var tvtime1 : TextView
    private lateinit var editButton : Button
    private lateinit var saveButton : Button

    private lateinit var tvtime2 : TextView
    private lateinit var editButton2 : Button
    private lateinit var saveButton2 : Button

    private lateinit var tvtime3 : TextView
    private lateinit var editButton3 : Button
    private lateinit var saveButton3 : Button
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jadwal)

        firestore = FirebaseFirestore.getInstance()
        // Mendapatkan referensi dokumen dari Firestore
        val jadwalPakanRef = firestore.collection("jadwal_pakan")

        tvtime1  = findViewById(R.id.tvtime1)
        editButton = findViewById(R.id.editButton )
        saveButton = findViewById(R.id.saveButton)



        // Mendapatkan data dari Firestore
        jadwalPakanRef.document("jadwal_pakan1").get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    // Dokumen ada, ambil nilai properti "time" dari dokumen
                    val timeValue = document.getString("time")

                    // Atur nilai timeValue ke tvTime
                    tvtime1.text = timeValue
                } else {
                    // Dokumen tidak ditemukan, atur nilai default "00:00" ke tvTime
                    tvtime1.text = "00:00"
                }
            }
            .addOnFailureListener { exception ->
                // Gagal mengambil data dari Firestore, tampilkan pesan kesalahan
                Toast.makeText(this, "Gagal mengambil data dari Firestore: ${exception.message}", Toast.LENGTH_SHORT).show()
            }


        editButton.setOnClickListener{
            val currentTime = Calendar.getInstance()
            val startHour = currentTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentTime.get(Calendar.MINUTE)

            TimePickerDialog(this,TimePickerDialog.OnTimeSetListener { view, hourofDay, minute ->
                tvtime1 .setText("$hourofDay : $minute")
            }, startHour,startMinute, true).show()
        }

        saveButton.setOnClickListener {
            val timeValue = tvtime1.text.toString()

        // Simpan nilai tvtime ke Firestore
            val jadwalPakanRef = firestore.collection("jadwal_pakan").document("jadwal_pakan1")
            val data = hashMapOf("time" to timeValue)
            Log.d("Cek",data.toString());

            jadwalPakanRef.set(data)
                .addOnSuccessListener {
                    Toast.makeText(this, "Nilai berhasil disimpan di Firestore", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Gagal menyimpan nilai di Firestore", Toast.LENGTH_SHORT).show()
                }

        }

        tvtime2  = findViewById(R.id.tvtime2)
        editButton2 = findViewById(R.id.editButton2 )
        saveButton2 = findViewById(R.id.saveButton2)

        // Mendapatkan data dari Firestore
        jadwalPakanRef.document("jadwal_pakan2").get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    // Dokumen ada, ambil nilai properti "time" dari dokumen
                    val timeValue = document.getString("time")

                    // Atur nilai timeValue ke tvTime
                    tvtime2.text = timeValue
                } else {
                    // Dokumen tidak ditemukan, atur nilai default "00:00" ke tvTime
                    tvtime2.text = "00:00"
                }
            }
            .addOnFailureListener { exception ->
                // Gagal mengambil data dari Firestore, tampilkan pesan kesalahan
                Toast.makeText(this, "Gagal mengambil data dari Firestore: ${exception.message}", Toast.LENGTH_SHORT).show()
            }

        editButton2.setOnClickListener{
            val currentTime = Calendar.getInstance()
            val startHour = currentTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentTime.get(Calendar.MINUTE)

            TimePickerDialog(this,TimePickerDialog.OnTimeSetListener { view, hourofDay, minute ->
                tvtime2 .setText("$hourofDay : $minute")
            }, startHour,startMinute, true).show()
        }

        saveButton2.setOnClickListener {
            val timeValue = tvtime2.text.toString()

            // Simpan nilai tvtime ke Firestore
            val jadwalPakanRef = firestore.collection("jadwal_pakan").document("jadwal_pakan2")
            val data = hashMapOf("time" to timeValue)
            Log.d("Cek",data.toString());

            jadwalPakanRef.set(data)
                .addOnSuccessListener {
                    Toast.makeText(this, "Nilai berhasil disimpan di Firestore", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Gagal menyimpan nilai di Firestore", Toast.LENGTH_SHORT).show()
                }

        }

        tvtime3  = findViewById(R.id.tvtime3)
        editButton3 = findViewById(R.id.editButton3 )
        saveButton3 = findViewById(R.id.saveButton3)

        // Mendapatkan data dari Firestore
        jadwalPakanRef.document("jadwal_pakan3").get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    // Dokumen ada, ambil nilai properti "time" dari dokumen
                    val timeValue = document.getString("time")

                    // Atur nilai timeValue ke tvTime
                    tvtime3.text = timeValue
                } else {
                    // Dokumen tidak ditemukan, atur nilai default "00:00" ke tvTime
                    tvtime3.text = "00:00"
                }
            }
            .addOnFailureListener { exception ->
                // Gagal mengambil data dari Firestore, tampilkan pesan kesalahan
                Toast.makeText(this, "Gagal mengambil data dari Firestore: ${exception.message}", Toast.LENGTH_SHORT).show()
            }


        editButton3.setOnClickListener{
            val currentTime = Calendar.getInstance()
            val startHour = currentTime.get(Calendar.HOUR_OF_DAY)
            val startMinute = currentTime.get(Calendar.MINUTE)

            TimePickerDialog(this,TimePickerDialog.OnTimeSetListener { view, hourofDay, minute ->
                tvtime3 .setText("$hourofDay : $minute")
            }, startHour,startMinute, true).show()
        }

        saveButton3.setOnClickListener {
            val timeValue = tvtime3.text.toString()

            // Simpan nilai tvtime ke Firestore
            val jadwalPakanRef = firestore.collection("jadwal_pakan").document("jadwal_pakan3")
            val data = hashMapOf("time" to timeValue)
            Log.d("Cek",data.toString());

            jadwalPakanRef.set(data)
                .addOnSuccessListener {
                    Toast.makeText(this, "Nilai berhasil disimpan di Firestore", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Gagal menyimpan nilai di Firestore", Toast.LENGTH_SHORT).show()
                }

        }

        val img_back = findViewById<ImageView>(R.id.img_back)
        img_back.setOnClickListener {
            val intent = Intent(this, ControlingActivity::class.java)
            startActivity(intent)
        }
    }
}