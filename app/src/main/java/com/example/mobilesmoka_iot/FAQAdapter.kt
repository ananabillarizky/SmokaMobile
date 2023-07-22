package com.example.mobilesmoka_iot

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FAQAdapter(private val faqList: List<FAQItem>) : RecyclerView.Adapter<FAQAdapter.FAQViewHolder>() {

    inner class FAQViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewQuestion: TextView = itemView.findViewById(R.id.textViewQuestion)
        val imageViewArrow: ImageView = itemView.findViewById(R.id.imageViewArrow)
        val textViewAnswer: TextView = itemView.findViewById(R.id.textViewAnswer)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val faqItem = faqList[position]
                    faqItem.isExpanded = !faqItem.isExpanded
                    notifyItemChanged(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FAQViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.itemfaq, parent, false)
        return FAQViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FAQViewHolder, position: Int) {
        val faqItem = faqList[position]
        holder.textViewQuestion.text = faqItem.pertanyaan
        holder.textViewAnswer.text = faqItem.jawaban

        if (faqItem.isExpanded) {
            holder.imageViewArrow.setImageResource(R.drawable.baseline_keyboard_arrow_down_24)
            holder.textViewAnswer.visibility = View.VISIBLE
        } else {
            holder.imageViewArrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_right_24)
            holder.textViewAnswer.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return faqList.size
    }
}
