package com.example.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
        val languageCheck = sharedPreferences.getBoolean("LANGUAGE", false)
        val registrationCheck = sharedPreferences.getBoolean("REGISTER",false)

        setContentView(R.layout.activity_main)
        Handler().postDelayed(Runnable {
            if(languageCheck && !registrationCheck){
                val intent = Intent(applicationContext, Registration::class.java)
                startActivity(intent)
                finish()
            }
            else if(languageCheck && registrationCheck){
                val intent = Intent(applicationContext, Dashboard::class.java)
                startActivity(intent)
                finish()
            }
            else{
                val intent = Intent(this@MainActivity, SelectLanguage::class.java)
                startActivity(intent)
                finish()
            }
        }, 1000L)
    }
}
