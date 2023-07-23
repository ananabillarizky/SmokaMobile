package com.example.mobilesmoka_iot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var img_back: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        img_back = findViewById(R.id.img_back)

        img_back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        recyclerView = findViewById(R.id.rvHistory)
        recyclerView.layoutManager = LinearLayoutManager(this)
        historyAdapter = HistoryAdapter()
        recyclerView.adapter = historyAdapter


    }
}