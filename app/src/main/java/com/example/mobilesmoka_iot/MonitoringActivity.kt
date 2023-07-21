package com.example.mobilesmoka_iot

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MonitoringActivity : AppCompatActivity() {
    private lateinit var txtC: TextView
    private lateinit var txtsuhuket: TextView
    private lateinit var txtPH: TextView
    private lateinit var txtphket: TextView
    private lateinit var txttds: TextView
    private lateinit var tdsket: TextView
    private lateinit var txtingkamakan: TextView
    private lateinit var txtmakanket: TextView
    private lateinit var img_back: ImageView
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monitoring)
        // Inisialisasi Firebase Database
        database = FirebaseDatabase.getInstance().reference
        txtC = findViewById(R.id.txtC)
        txtsuhuket = findViewById(R.id.txtsuhuket)
        txtPH = findViewById(R.id.txtPH)
        txtphket = findViewById(R.id.txtphket)
        txttds = findViewById(R.id.txttds)
        tdsket = findViewById(R.id.tdsket)
        txtingkamakan = findViewById(R.id.txtingkamakan)
        txtmakanket = findViewById(R.id.txtmakanket)

        val img_back = findViewById<ImageView>(R.id.img_back)
        img_back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        // Mendapatkan referensi ke node "Monitoring"
        val monitoringRef = database.child("Monitoring")

        val eventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.hasChild("suhu")) {
                    val suhu = dataSnapshot.child("suhu").value.toString()
                    val suhuDenganC = "$suhu°C"
                    txtC.text = suhuDenganC
                }
                if (dataSnapshot.hasChild("keteranganSuhu")) {
                    val keteranganSuhu = dataSnapshot.child("keteranganSuhu").value.toString()
                    txtsuhuket.text = keteranganSuhu
                }
                if (dataSnapshot.hasChild("ph")) {
                    val ph = dataSnapshot.child("ph").value.toString()
                    txtPH.text = ph
                }
                if (dataSnapshot.hasChild("keteranganpH")) {
                    val keteranganpH = dataSnapshot.child("keteranganpH").value.toString()
                    txtphket.text = keteranganpH
                }
                if (dataSnapshot.hasChild("turbidity")) {
                    val turbidity = dataSnapshot.child("turbidity").value.toString()
                    txttds.text = turbidity
                }
                if (dataSnapshot.hasChild("keteranganTurbidity")) {
                    val keteranganTurbidity = dataSnapshot.child("keteranganTurbidity").value.toString()
                    tdsket.text = keteranganTurbidity
                }
                if (dataSnapshot.hasChild("jarak")) {
                    val jarak = dataSnapshot.child("jarak").value.toString()
                    val jarakDenganCm = "$jarak cm"
                    txtingkamakan.text = jarakDenganCm
                }

                if (dataSnapshot.hasChild("keteranganJarak")) {
                    val keteranganJarak = dataSnapshot.child("keteranganJarak").value.toString()
                    txtmakanket.text = keteranganJarak
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Penanganan kesalahan, jika diperlukan
            }
        }

        // Menambahkan event listener ke node "Monitoring"
        monitoringRef.addValueEventListener(eventListener)
    }
}
