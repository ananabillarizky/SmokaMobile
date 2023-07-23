package com.example.mobilesmoka_iot

import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.os.Bundle
import android.view.View
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.checkerframework.checker.units.qual.Length


class InformationActivity : AppCompatActivity() {

    private lateinit var expandableListView: ExpandableListView
    private lateinit var expandableListAdapter: ExpandableListAdapter
    private lateinit var groupList: List<String>
    private lateinit var childMap: Map<String, List<String>>
    private lateinit var  imageViewArrow :ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        expandableListView = findViewById(R.id.expandableListView)

        groupList = listOf("Anggota", "Dosen Pembimbing")
        childMap = mapOf(
            "Anggota" to listOf("Ananabilla Rizky Muhardanie", "Hamdan Yuwafi"),
            "Dosen Pembimbing" to listOf("Slamet Handoko, S.Kom., M.Kom.", "Kuwat Santoso, S.Kom., M.Kom.")
        )

        // Buat adapter untuk ExpandableListView
        expandableListAdapter = MyExpandableListAdapter(this, groupList, childMap)

        // Set adapter ke ExpandableListView
        expandableListView.setAdapter(expandableListAdapter)

        // Set listener untuk menghandle klik pada grup
        expandableListView.setOnGroupClickListener { _, _, groupPosition, _ ->
            toggleGroup(groupPosition)
            true
        }

        // Back ke home atau main activity
        val img_back = findViewById<ImageView>(R.id.img_back)
        img_back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun toggleGroup(groupPosition: Int) {
        if (expandableListView.isGroupExpanded(groupPosition)) {
            expandableListView.collapseGroup(groupPosition)
//            imageViewArrow.setImageResource(R.drawable.baseline_keyboard_arrow_down_24)

        } else {
            expandableListView.expandGroup(groupPosition)
//            imageViewArrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_right_24)

        }
    }


}
