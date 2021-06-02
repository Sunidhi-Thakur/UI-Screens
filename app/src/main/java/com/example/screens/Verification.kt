package com.example.screens

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.screens.databinding.ActivityVerificationBinding


class Verification : AppCompatActivity() {
    lateinit var binding: ActivityVerificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.box1.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (binding.box1.text.toString().length == 1) {
                    binding.box1.clearFocus()
                    binding.box2.requestFocus()
                    binding.box2.isCursorVisible = true
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (binding.box1.text.toString().isEmpty()) {
                    binding.box1.requestFocus()
                }
            }
        })


        binding.box2.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (binding.box2.text.toString().length == 1) {
                    binding.box2.clearFocus()
                    binding.box3.requestFocus()
                    binding.box3.isCursorVisible = true

                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                if (binding.box2.text.toString().isEmpty()) {
                    binding.box2.requestFocus()
                }

            }
        })

        binding.box3.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (binding.box3.text.toString().length == 1) {
                    binding.box3.clearFocus()
                    binding.box4.requestFocus()
                    binding.box4.isCursorVisible = true
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                if (binding.box3.text.toString().isEmpty()) {
                    binding.box3.requestFocus()
                }

            }
        })


        binding.box4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (binding.box4.text.toString().length == 1) {
                    binding.box4.requestFocus()
                    binding.box4.isCursorVisible = true

                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        object : CountDownTimer(600000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.txtClok.text = ((String.format("%02d",millisUntilFinished/ 60000))+":"+String.format("%02d",millisUntilFinished % 60000 / 1000))
            }

            override fun onFinish() {
            }
        }.start()

        binding.submitButton.setOnClickListener {
            val intent = Intent(this@Verification, Congratulations::class.java)
            startActivity(intent)
        }
    }
}

