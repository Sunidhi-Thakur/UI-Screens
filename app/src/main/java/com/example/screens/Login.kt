package com.example.screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.screens.databinding.ActivityLoginBinding
import com.example.screens.models.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.loginBtn.setOnClickListener {
            getOTP()
            val intent = Intent(this@Login, Verification::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getOTP() {
        val otp: Call<LoginResponse> = LoginAPI.loginInstance.getOTP(binding.contEdit.toString())
        otp.enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val otp: LoginResponse? = response.body()
                if (otp != null){
                    Toast.makeText(this@Login, otp.msg, Toast.LENGTH_SHORT).show()
                    Log.d("SUNIDHI", response.body().toString())
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("SUNIDHI", "Error")
            }

        })
    }

}