package com.joel.flySyApp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.joel.flySyApp.feature.auth.signin.SignInScreen
import com.joel.flySyApp.feature.home.HomeScreen
import kotlinx.serialization.Serializable

// AUTH SCREENS
@Serializable object SignIn
@Serializable object ForgotPass

@Serializable object SignUp
@Serializable object VerifySignUp

// MAIN SCREENS
@Serializable data class Home(val userId: String)

@Composable
fun RootNavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = SignIn,
    ) {
        
        composable<SignIn> {
            SignInScreen(
                modifier = Modifier.padding(paddingValues),
                onLoginSuccess = { id -> navController.navigate( Home(id) ) },
                onSignUpClick = {},
                onSignUpWithGoogle = {},
                onForgotPassword = {}
            )
        }

        composable<Home> { backStackEntry ->
            val home: Home = backStackEntry.toRoute()
            HomeScreen(
                userId = home.userId,
                onLogOut = {
                    navController.popBackStack(
                        route = SignIn,
                        inclusive = false
                    )}

            )
        }

    }
}