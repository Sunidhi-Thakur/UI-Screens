package com.example.screens.models

import com.google.gson.annotations.SerializedName

data class OtpResponse(@SerializedName("code")
                       val code: Int,
                       @SerializedName("msg")
                       val msg: String)
