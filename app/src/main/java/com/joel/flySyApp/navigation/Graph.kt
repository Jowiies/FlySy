package com.joel.flySyApp.navigation

sealed class Graph(val route: String) {
    object Auth : Graph("auth") {
        object SignIn : Graph("signin") {
            const val Main = "signin/main"
            const val ForgotPassword = "signin/forgot_password"
        }
        object SignUp : Graph("signup") {
            const val Main = "signup/main"
            const val Verification = "signup/verification"
        }
    }

    object Home : Graph("home") {
        const val Main = "home/main"
    }
}