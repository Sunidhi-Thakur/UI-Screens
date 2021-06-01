package com.example.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.screens.adapters.DashboardAdapter
import com.example.screens.databinding.ActivityDashboardBinding
import com.example.screens.models.DashboardList
import java.util.*

class Dashboard : AppCompatActivity() {
    lateinit var binding: ActivityDashboardBinding
    private val dashboardList = ArrayList<DashboardList>()
    private lateinit var dashboardAdapter: DashboardAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dashboardAdapter = DashboardAdapter(dashboardList)
        val layoutManager = LinearLayoutManager(applicationContext)
        binding.dashboardRV.layoutManager = layoutManager
        binding.dashboardRV.adapter = dashboardAdapter
        binding.dashboardRV.isNestedScrollingEnabled = false

        prepareData()

    }

    private fun prepareData() {
        var item = DashboardList(R.drawable.life_insurance, "Life Insurance", "15 hours")
        dashboardList.add(item)
        item = DashboardList(R.drawable.life_insurance, "Life Insurance", "15 hours")
        dashboardList.add(item)
        dashboardAdapter.notifyDataSetChanged()
    }
}