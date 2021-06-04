package com.example.screens

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.screens.databinding.ActivityVerificationBinding
import com.example.screens.models.LoginResponse
import com.example.screens.models.OtpResponse


class Verification : AppCompatActivity() {
    lateinit var binding: ActivityVerificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.box1.addTextChangedListener(GenericTextWatcher(binding.box1, binding.box2))
        binding.box2.addTextChangedListener(GenericTextWatcher(binding.box2, binding.box3))
        binding.box3.addTextChangedListener(GenericTextWatcher(binding.box3, binding.box4))
        binding.box4.addTextChangedListener(GenericTextWatcher(binding.box4, null))

        binding.box1.setOnKeyListener(GenericKeyEvent(binding.box1, null))
        binding.box2.setOnKeyListener(GenericKeyEvent(binding.box2, binding.box1))
        binding.box3.setOnKeyListener(GenericKeyEvent(binding.box3, binding.box2))
        binding.box4.setOnKeyListener(GenericKeyEvent(binding.box4, binding.box3))

        object : CountDownTimer(600000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.txtClok.text = ((String.format(
                    "%02d",
                    millisUntilFinished / 60000
                )) + ":" + String.format("%02d", millisUntilFinished % 60000 / 1000))
            }

            override fun onFinish() {
            }
        }.start()

        binding.backArrow.setOnClickListener {
            val intent = Intent(this@Verification, Login::class.java)
            startActivity(intent)
        }


        AndroidNetworking.initialize(this)
        AndroidNetworking.post("https://innowrap.co.in/clients/acuvisor/App/User/VerifyOtp")
            .addHeaders("Authorization", "Basic TzdOZVBeQjNnTjkhYT9FOik2KF40K1hJY0JYTWhpTkY0OT05")
            .addHeaders("Token", "gCERLnNSEx9HGbOy1AmMKg==")
            .addHeaders("Content-Type","application/x-www-form-urlencoded")
            .addUrlEncodeFormBodyParameter("otp","1111").build().getAsObject(OtpResponse::class.java, object : ParsedRequestListener<OtpResponse> {
                    override fun onResponse(response: OtpResponse) {
                        Log.d("SUNIDHI", response.msg)
                        if (response.msg == "Verify Done") {
                            binding.submitButton.setOnClickListener {
                                if (binding.box1.text.length == 1 && binding.box2.text.length == 1 && binding.box3.text.length == 1 && binding.box4.text.length == 1) {
                                    val intent = Intent(
                                        this@Verification,
                                        Congratulations::class.java
                                    )
                                    startActivity(intent)
                                } else {
                                    Toast.makeText(
                                        this@Verification,
                                        "Enter OTP",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                        } else {
                            binding.submitButton.setOnClickListener {
                                Toast.makeText(this@Verification, response.msg, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }

                    override fun onError(anError: ANError?) {
                        Toast.makeText(this@Verification, R.string.error, Toast.LENGTH_SHORT).show()
                    }
                })

        binding.resend.setOnClickListener {
            Toast.makeText(this, "Sending OTP ...", Toast.LENGTH_SHORT).show()
            AndroidNetworking.post("https://innowrap.co.in/clients/acuvisor/App/User/ResendOtp")
                .addBodyParameter("mobile", "ZROq0iLVFUdqR6Lw0jXzOA==").addHeaders("Authorization","Basic TzdOZVBeQjNnTjkhYT9FOik2KF40K1hJY0JYTWhpTkY0OT05").build().getAsObject(
                    LoginResponse::class.java,
                    object : ParsedRequestListener<LoginResponse> {
                        override fun onResponse(response: LoginResponse) {
                            Toast.makeText(this@Verification, response.msg, Toast.LENGTH_SHORT).show()
                            Log.d("SUNIDHI", response.Token)
                        }
                        override fun onError(anError: ANError?) {
                            Toast.makeText(this@Verification, R.string.error, Toast.LENGTH_SHORT).show()
                        }
                    })
                    }
        }




    }

    class GenericKeyEvent internal constructor(
        private val currentView: EditText,
        private val previousView: EditText?
    ) : View.OnKeyListener {
        override fun onKey(p0: View?, keyCode: Int, event: KeyEvent?): Boolean {
            if (event!!.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL && currentView.id != R.id.box1 && currentView.text.isEmpty()) {
                previousView!!.text = null
                previousView.requestFocus()
                return true
            }
            return false
        }


    }

    class GenericTextWatcher internal constructor(
        private val currentView: View,
        private val nextView: View?
    ) : TextWatcher {
        override fun afterTextChanged(editable: Editable) {
            val text = editable.toString()
            when (currentView.id) {
                R.id.box1 -> if (text.length == 1) nextView!!.requestFocus()
                R.id.box2 -> if (text.length == 1) nextView!!.requestFocus()
                R.id.box3 -> if (text.length == 1) nextView!!.requestFocus()
                R.id.box4 -> if (text.length == 1) nextView?.requestFocus()

            }
        }

        override fun beforeTextChanged(
            arg0: CharSequence,
            arg1: Int,
            arg2: Int,
            arg3: Int
        ) {
        }

        override fun onTextChanged(
            arg0: CharSequence,
            arg1: Int,
            arg2: Int,
            arg3: Int
        ) {
        }

    }



