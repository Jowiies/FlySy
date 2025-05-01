package com.joel.flySyApp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.joel.flySyApp.feature.auth.signin.SignInScreen
import com.joel.flySyApp.feature.auth.signin.ForgotPasswordScreen
import com.joel.flySyApp.feature.auth.signup.SignUpScreen
import com.joel.flySyApp.feature.auth.signup.VerificationScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        startDestination = Graph.Auth.SignIn.route,
        route = Graph.Auth.route
    ) {
        signInNavGraph(navController)
        signUpNavGraph(navController)
    }
}

private fun NavGraphBuilder.signInNavGraph(navController: NavHostController) {
    navigation(
        startDestination = Graph.Auth.SignIn.Main,
        route = Graph.Auth.SignIn.route
    ) {
        composable(Graph.Auth.SignIn.Main) {
            SignInScreen(
                onLoginSuccess = {
                    navController.navigate(Graph.Home.Main){
                        popUpTo(Graph.Auth.route) {inclusive = true}
                    }
                },
                onSignUpClick = { navController.navigate(Graph.Auth.SignUp.Main) },
                onForgotPassword = { navController.navigate(Graph.Auth.SignIn.ForgotPassword) },
                onSignUpWithGoogle = {/* Temporal */}
            )
        }

        composable(Graph.Auth.SignIn.ForgotPassword) {
            ForgotPasswordScreen(
                onBack = { navController.popBackStack() },
                onSubmit = { /* Handle password reset */ }
            )
        }
    }
}

private fun NavGraphBuilder.signUpNavGraph(navController: NavHostController) {
    navigation(
        startDestination = Graph.Auth.SignUp.Main,
        route = Graph.Auth.SignUp.route
    ) {
        composable(Graph.Auth.SignUp.Main) {
            SignUpScreen(
                onSignUpSuccess = { navController.navigate(Graph.Auth.SignUp.Verification) },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Graph.Auth.SignUp.Verification) {
            VerificationScreen(
                onVerified = {
                    navController.navigate(Graph.Home.Main) {
                        popUpTo(Graph.Auth.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
