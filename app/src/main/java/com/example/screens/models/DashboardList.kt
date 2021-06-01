package com.example.screens.models

class DashboardList(image_link: Int?, heading: String?, time: String?){
    private var image_link: Int = image_link!!
    private var heading: String = heading!!
    private var time: String = time!!
    fun getImage(): Int? {
        return image_link
    }
    fun getHeading(): String? {
        return heading
    }
    fun getTime(): String? {
        return time
    }
}