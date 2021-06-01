package com.example.screens

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.screens.databinding.ActivityRegistrationBinding

class Registration : AppCompatActivity() {
    lateinit var binding: ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.submitButton.setOnClickListener {
            val intent = Intent(this@Registration, Verification::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun onBrowse(view: View){
        val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivity(i)
    }

    fun goToLanguage(view: View) {
        val intent = Intent(this@Registration, SelectLanguage::class.java)
        startActivity(intent)
        finish()
    }

}