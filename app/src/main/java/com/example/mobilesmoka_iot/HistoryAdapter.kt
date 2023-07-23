package com.example.mobilesmoka_iot

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilesmoka_iot.model.HistoryModel
import com.google.firebase.database.*

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private val historyList = ArrayList<HistoryModel>()
    private val databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().reference.child("History")

    init {
        // Load data from Firebase Realtime Database
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                historyList.clear()
                for (dataSnapshot in snapshot.children) {
                    val historyData = dataSnapshot.getValue(HistoryModel::class.java)
                    historyData?.let { historyList.add(it) }
                    Log.d("CEKDATA",historyData.toString())
                }
                notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database error if needed
            }
        })
    }

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTimestamp: TextView = itemView.findViewById(R.id.tvTimestamp)
        private val tvSuhuAir: TextView = itemView.findViewById(R.id.tvSuhuAir)
        private val tvPhAir: TextView = itemView.findViewById(R.id.tvPhAir)
        private val tvKekeruhanAir: TextView = itemView.findViewById(R.id.tvKekeruhanAir)
        private val tvPakan: TextView = itemView.findViewById(R.id.tvPakan)

        fun bind(historyData: HistoryModel) {
            tvTimestamp.text = historyData.timestamp
            tvSuhuAir.text = historyData.suhuAir
            tvPhAir.text = historyData.phair
            tvKekeruhanAir.text = historyData.kekeruhanAir
            tvPakan.text = historyData.pakan
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val currentItem = historyList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }


}