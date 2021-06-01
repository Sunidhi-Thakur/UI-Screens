package com.example.screens

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter (private var title: List<Int>, private var details: List<Int>, private var image: List<Int>): RecyclerView.Adapter<ViewPagerAdapter.Pager2ViewHolder>(){
    inner class Pager2ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val itemTitle: TextView = itemView.findViewById(R.id.slide1_heading)
        val itemDetail: TextView = itemView.findViewById(R.id.slide1_desc)
        val itemImage: ImageView = itemView.findViewById(R.id.imageSlider)

        init {
            val position: Int = adapterPosition
            itemImage.setOnClickListener {
                Toast.makeText(
                    itemView.context,
                    "You clicked on item #${position + 1}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerAdapter.Pager2ViewHolder {
            return Pager2ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false)
        )

    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.Pager2ViewHolder, position: Int) {
        holder.itemTitle.setText(title[position])
        holder.itemDetail.setText(details[position])
        holder.itemImage.setImageResource(image[position])


    }

    override fun getItemCount(): Int {
        return title.size
    }
}