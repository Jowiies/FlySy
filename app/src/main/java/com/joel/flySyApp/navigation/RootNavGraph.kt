package com.joel.flySyApp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.joel.flySyApp.feature.auth.signin.SignInScreen
import kotlinx.serialization.Serializable

// AUTH SCREENS
@Serializable object SignIn
@Serializable object ForgotPass

@Serializable object SignUp
@Serializable object VerifySignUp

// MAIN SCREENS
@Serializable data class Profile(val name: String)
@Serializable object Home

@Composable
fun RootNavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = SignIn,
    ) {
        
        composable<SignIn> {
            SignInScreen(
                onLoginSuccess = TODO(),
                onSignUpClick = TODO(),
                onSignUpWithGoogle = TODO(),
                onForgotPassword = TODO()
            )
        }

        composable<Home> {
            
        }

    }
}