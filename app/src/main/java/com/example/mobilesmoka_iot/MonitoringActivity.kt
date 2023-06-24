package com.example.mobilesmoka_iot

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MonitoringActivity : AppCompatActivity() {
    private lateinit var txtC: TextView
    private lateinit var txtsuhuket: TextView

    companion object {
        private const val TAG = "MonitoringActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monitoring)

        txtC = findViewById(R.id.txtC)
        txtsuhuket = findViewById(R.id.txtsuhuket)

        val database = FirebaseDatabase.getInstance()
        val monitoringRef = database.reference.child("Monitoring")

        monitoringRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    val suhu = snapshot.child("suhu").getValue(String::class.java)
                    val keteranganSuhu = snapshot.child("keteranganSuhu").getValue(String::class.java)

                    // Perbarui TextView dengan data yang sesuai
                    txtC.text = "$suhu"
                    txtsuhuket.text = "$keteranganSuhu"

                    txtC.text = "${suhu ?: "Data tidak tersedia"}"
                    txtsuhuket.text = "${keteranganSuhu ?: "Data tidak tersedia"}"
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Tangani kesalahan baca database
                Log.e(TAG, "Gagal membaca data monitoring: ${databaseError.message}")
            }
        })
    }
}
