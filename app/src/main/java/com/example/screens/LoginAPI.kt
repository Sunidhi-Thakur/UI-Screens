package com.example.screens

import com.example.screens.models.LoginResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

const val BASE_URL = "https://innowrap.co.in/clients/acuvisor/App/User/"

interface LoginInterface {

   @Headers("Content-Type: application/json", "Authorization: Basic TzdOZVBeQjNnTjkhYT9FOik2KF40K1hJY0JYTWhpTkY0OT05")
    @POST("ResendOtp/")
    fun getOTP(@Body mobile: String): Call<LoginResponse>
}

object LoginAPI{
    val loginInstance: LoginInterface
    init{
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        loginInstance = retrofit.create(LoginInterface::class.java)
    }
}