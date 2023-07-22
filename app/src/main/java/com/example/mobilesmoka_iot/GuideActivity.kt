package com.example.mobilesmoka_iot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import com.google.common.reflect.TypeToken

class GuideActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var faqAdapter: FAQAdapter

    private val faqList = mutableListOf<FAQItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide)
        // Back ke home atau main activity
        val img_back = findViewById<ImageView>(R.id.img_back)
        img_back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerViewFAQ)
        recyclerView.layoutManager = LinearLayoutManager(this)
        faqAdapter = FAQAdapter(faqList)
        recyclerView.adapter = faqAdapter

        // Load JSON data from assets
        val json = loadJSONFromAsset("faq_data.JSON")
        if (!json.isNullOrBlank()) {
            val parsedFAQList = parseFAQDataFromJSON(json)
            faqList.addAll(parsedFAQList)
            faqAdapter.notifyDataSetChanged()
        } else {
            // Handle case when JSON data is empty or not found
        }
    }

    private fun loadJSONFromAsset(fileName: String): String? {
        return try {
            val inputStream: InputStream = assets.open(fileName)
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            bufferedReader.use { it.readText() }
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    private fun parseFAQDataFromJSON(jsonString: String): List<FAQItem> {
        val listType = object : TypeToken<List<FAQItem>>() {}.type
        return Gson().fromJson(jsonString, listType)
    }
}



