package com.example.screens

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class Congratulations : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_congratulations)
        Handler().postDelayed(Runnable {
            val sharedPreferences = getSharedPreferences("SP_INFO", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putBoolean("REGISTER", true)
            editor.apply()
            val intent = Intent(this@Congratulations, Dashboard::class.java)
            startActivity(intent)
            finish()
        }, 1000L)
    }
}