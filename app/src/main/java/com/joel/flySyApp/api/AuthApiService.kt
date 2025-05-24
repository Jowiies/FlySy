package com.joel.flySyApp.api

import com.joel.flySyApp.feature.auth.signin.model.SignInData
import com.joel.flySyApp.feature.auth.signin.model.TokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("login")
    suspend fun signIn(@Body request: SignInData): TokenResponse
}