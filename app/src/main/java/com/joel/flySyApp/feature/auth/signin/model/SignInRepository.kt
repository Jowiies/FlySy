package com.joel.flySyApp.feature.auth.signin.model

import com.joel.flySyApp.RetrofitInstance

class SignInRepository {
    suspend fun signIn(email: String, password: String): TokenResponse {
        val request = SignInData(email, password)
        return RetrofitInstance.api.signIn(request)
    }
}