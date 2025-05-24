package com.joel.flySyApp

import com.joel.flySyApp.api.AuthApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://192.168.1.51:8000/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: AuthApiService = retrofit.create(AuthApiService::class.java)
}