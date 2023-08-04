package com.example.mobilesmoka_iot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var img_back: ImageView
    private lateinit var btn_grafik: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        img_back = findViewById(R.id.img_back)
        btn_grafik = findViewById(R.id.btnGrafik)

        img_back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_grafik.setOnClickListener {
            val intent = Intent(this, GrafikHistoryActivity::class.java)
            startActivity(intent)
        }

        var svHistory = findViewById<SearchView>(R.id.svHistory)
        svHistory.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                historyAdapter.filterByDate(newText ?: "")
                return true
            }
        })

        recyclerView = findViewById(R.id.rvHistory)
        recyclerView.layoutManager = LinearLayoutManager(this)
        historyAdapter = HistoryAdapter()
        recyclerView.adapter = historyAdapter


    }
}