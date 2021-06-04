package com.example.screens.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("Token")
    val Token: String,
    @SerializedName("msg")
    val msg: String)