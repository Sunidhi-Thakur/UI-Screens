package com.example.screens.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.screens.R
import com.example.screens.adapters.DashboardAdapter
import com.example.screens.databinding.FragmentHomeBinding
import com.example.screens.models.DashboardList

class Home : Fragment() {

    private val dashboardList = ArrayList<DashboardList>()
    private lateinit var dashboardAdapter: DashboardAdapter
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        val dashboardRecyclerView = rootView.findViewById(R.id.dashboardRV) as RecyclerView
        binding = FragmentHomeBinding.inflate(layoutInflater)
        dashboardAdapter = DashboardAdapter(dashboardList)
       dashboardRecyclerView.layoutManager = LinearLayoutManager(activity)
        dashboardRecyclerView.adapter = dashboardAdapter
        prepareData()
        return rootView

    }

    private fun prepareData() {
        var item = DashboardList(R.drawable.life_insurance, "Life Insurance", "15 hours")
        dashboardList.add(item)
        item = DashboardList(R.drawable.insurance_img2, "Travel Insurance", "15 hours")
        dashboardList.add(item)
        item = DashboardList(R.drawable.insurance_img3, "How to do a business?", "5 hours")
        dashboardList.add(item)
        dashboardAdapter.notifyDataSetChanged()
    }


}