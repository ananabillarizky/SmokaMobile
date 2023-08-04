package com.example.mobilesmoka_iot;

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description


class GrafikHistoryActivity : AppCompatActivity() {
    private lateinit var img_back: ImageView

    private lateinit var  lCHistory: LineChart

    private lateinit var  xValues:List<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grafik_history)

        img_back = findViewById(R.id.img_back)
        lCHistory = findViewById(R.id.chart)

        val desc = Description()
        desc.text = "Grafik"
        desc.setPosition(150f, 15f)
        lCHistory.description = desc
        lCHistory.axisRight.setDrawLabels(false)


        img_back.setOnClickListener {
            onBackPressed()
        }

    }
}
