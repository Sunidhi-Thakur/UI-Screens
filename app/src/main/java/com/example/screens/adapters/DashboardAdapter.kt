package com.example.screens.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.screens.R
import com.example.screens.models.DashboardList

class DashboardAdapter(private var dashboardList: List<DashboardList>):
    RecyclerView.Adapter<DashboardAdapter.MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dashboard, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = dashboardList[position]
        holder.heading.text = item.getHeading()
        holder.time.text = item.getTime()
        item.getImage()?.let { holder.image.setImageResource(it) }
    }

    override fun getItemCount(): Int {
        return dashboardList.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var heading: TextView = view.findViewById(R.id.headingD)
        var time: TextView = view.findViewById(R.id.timeD)
        var image: ImageView = view.findViewById(R.id.imageView4)
    }
}