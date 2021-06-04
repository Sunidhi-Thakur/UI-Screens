package com.example.screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.screens.databinding.ActivityLoginBinding
import com.example.screens.models.LoginResponse


class Login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.loginBtn.setOnClickListener {
            if (binding.contEdit.text.length != 10 ){
                Toast.makeText(this@Login, "Enter a valid number", Toast.LENGTH_SHORT).show()
            }else {
                AndroidNetworking.initialize(this)
                AndroidNetworking.post("https://innowrap.co.in/clients/acuvisor/App/User/ResendOtp")
                    .addBodyParameter("mobile", "ZROq0iLVFUdqR6Lw0jXzOA==").addHeaders("Authorization","Basic TzdOZVBeQjNnTjkhYT9FOik2KF40K1hJY0JYTWhpTkY0OT05").build().getAsObject(
                        LoginResponse::class.java,
                        object : ParsedRequestListener<LoginResponse> {
                            override fun onResponse(response: LoginResponse) {
                                Toast.makeText(this@Login, response.msg, Toast.LENGTH_SHORT).show()
                                Log.d("SUNIDHI", response.Token)
                            }
                            override fun onError(anError: ANError?) {
                                Toast.makeText(this@Login, R.string.error, Toast.LENGTH_SHORT).show()
                            }
                        })
                val intent = Intent(this@Login, Verification::class.java)
                startActivity(intent)
                finish()
            }
        }

    }
}