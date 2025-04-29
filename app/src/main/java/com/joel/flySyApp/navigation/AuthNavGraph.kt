package com.joel.flySyApp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavController
import androidx.navigation.navigation
import com.joel.flySyApp.feature.auth.LoginScreen

fun NavGraphBuilder.authGraph(navController: NavController) {
    navigation(
        startDestination = "login_screen",
        route = Graph.Auth.route,
    ) {
        composable("login_screen") { LoginScreen(navController) }
    }
}